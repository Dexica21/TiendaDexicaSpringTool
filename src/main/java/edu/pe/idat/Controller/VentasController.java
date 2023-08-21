package edu.pe.idat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

import edu.pe.idat.Model.*;
import edu.pe.idat.repository.*;
import edu.pe.idat.repository.DistritoRepository;
import edu.pe.idat.service.UsuarioService;
import edu.pe.idat.service.VentaService;

import org.springframework.ui.Model;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class VentasController {
	

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private DetalleVentaRepository detalleventaRepository;
	
	@Autowired
	private DistritoRepository distritoRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProductosRepository productoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@GetMapping("/Usuario/Ventas")
	public String Formu(Model model) {
		List<Distrito>distritos = distritoRepository.findAll();
		model.addAttribute("distritos", distritos);
		return "Venta.html";
	}
	
	
	@GetMapping("/Usuario/Informacion")
	public String InformacionP(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    Usuario usuario = usuarioRepository.findByNombreUsuario(username);

	    // Verificar si el usuario ya tiene una idPersona
	    if (usuario != null && usuario.getPersona() != null) {
	    	List<Distrito> distritos = distritoRepository.findAll(); 
		    model.addAttribute("distritos", distritos);
	        return "Venta.html";
	    }

	    List<Distrito> distritos = distritoRepository.findAll(); 
	    model.addAttribute("distritos", distritos);
	    model.addAttribute("persona", new Persona());
	    return "InformacionPersonal.html";
	}
	
	
	@PostMapping("/Usuario/Registrar")
	public String registrar(@ModelAttribute Persona persona) {
		personaRepository.save(persona);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		
	    Usuario usuario = usuarioRepository.findByNombreUsuario(username);
	    
	    if (usuario != null) {
	        usuario.setPersona(persona);
	        usuarioRepository.save(usuario);
	    }
	    
		return "redirect:/Usuario/Ventas";
	}
	
	
	@PostMapping("/Usuario/CargarPago")
	public ResponseEntity<String> cargarPago(@RequestBody VentaRequest ventaRequest) {
	    try {
	        Venta venta = new Venta();

	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	            Usuario usuarioAutenticado = usuarioRepository.findByNombreUsuario(userDetails.getUsername());
	            venta.setUsuario(usuarioAutenticado);
	        }

	        venta.setDistrito(ventaRequest.getDistrito());
	        venta.setDireccion(ventaRequest.getDireccion());
	        venta.setCantidadProducto(ventaRequest.getCantidadProducto());
	        venta.setMontoTotal(ventaRequest.getMontoTotal());
	        venta.setFechaVenta(LocalDateTime.now());
	        
	        ventaRepository.save(venta);

	        for (DetalleVentaRequest detalleRequest : ventaRequest.getDetalles()) {
	            DetalleVenta detalleVenta = new DetalleVenta();
	            detalleVenta.setVenta(venta);
	            detalleVenta.setProducto(detalleRequest.getProducto());
	            detalleVenta.setCantidad(detalleRequest.getCantidad());
	            detalleVenta.setTotal(detalleRequest.getTotal());
	            detalleVenta.setFechaVenta(LocalDateTime.now());
	            
	            detalleventaRepository.save(detalleVenta);
	            
	            Productos producto = productoRepository.findById(detalleRequest.getProducto().getIdProducto()).orElse(null);
	            if (producto != null) {
	                int cantidadAdquirida = detalleRequest.getCantidad();
	                int stockActual = producto.getStock();
	                int nuevoStock = stockActual - cantidadAdquirida;
	                producto.setStock(nuevoStock);
	                productoRepository.save(producto);
	            }
	            
	        }

	        return ResponseEntity.ok("Venta creada exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la venta: " + e.getMessage());
	    }
	}
	
	
	
	@GetMapping("/Usuario/Configuracion")
	public String ConfiguracionCuenta(Model model) {
		
		
		return "ConfiguracionUsuario.html";
	}
	
	@GetMapping("/Usuario/MisPedidos")
	public String MisPedidos(Model model) {
		
		return "MisPedidos.html";
	}

	
	
}
