package edu.pe.idat.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.pe.idat.DTO.ListaVentasDetalle;
import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Modelo;
import edu.pe.idat.Model.Productos;
import edu.pe.idat.Model.Usuario;
import edu.pe.idat.Model.Venta;
import edu.pe.idat.Model.Colores;
import edu.pe.idat.Model.DetalleVenta;
import edu.pe.idat.repository.ColoresRepository;
import edu.pe.idat.repository.DetalleVentaRepository;
import edu.pe.idat.repository.ProductosRepository;
import edu.pe.idat.repository.VentaRepository;
import edu.pe.idat.service.CategoriaService;
import edu.pe.idat.service.ColoresService;
import edu.pe.idat.service.ModeloService;
import edu.pe.idat.service.ProductosService;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class ProductoController {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	

	@GetMapping("/Catalogo/WishStop")
	public String WishStop(Model model) {
		return "/DetalleProducto/WishStop.html";
	}	
	
	@GetMapping("/Catalogo/index")
	public String index(Model model) {
		return "/DetalleProducto/index.html";
	}

	
	@GetMapping("/Catalogo/DeportivoBasico")
	public String DeportivoBasico(Model model) {
		return "/DetalleProducto/DeportivoBasico.html";
	}
	
	@GetMapping("/Catalogo/Deportivo")
	public String Deportivo(Model model) {
		return "/DetalleProducto/Deportivo2.html";
	}
	
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ModeloService modeloService;
	
	@Autowired
	private ColoresService coloresService;
	
	@GetMapping("/Administrador/Producto")
	public String AdministracionP(Model model) {
		List<Categoria> categorias = categoriaService.findAll();
		List<Modelo>modelos = modeloService.findAll();
		List<Colores> colores = coloresService.findAll();
	    model.addAttribute("colores", colores);
		model.addAttribute("categorias", categorias);
		model.addAttribute("modelos", modelos);
		model.addAttribute("productos",new Productos());
		return "Administrador/AgregarProductos.html";
	}
	
	@Autowired
	private ProductosService productosService;
	

	
	//Registrar Producto
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping( value = "/Administrador/AgregarProducto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String BotonFormulario(@ModelAttribute("productos") Productos productos, 
								  @RequestParam("imagenFile") MultipartFile imagenFile,
								  @RequestParam("colores") List<Integer> colorIds
			) {
		if (!imagenFile.isEmpty()) {
	        // Se proporcionó una imagen de archivo
	        try {
	            byte[] imagenBytes = imagenFile.getBytes();
	            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
	            productos.setImagen("data:" + imagenFile.getContentType() + ";base64," +imagenBase64);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        
	    }
		
		Set<Colores> coloresSeleccionados = coloresService.obtenerColoresPorIds(colorIds);
	    // Asignar los colores seleccionados al producto
	    productos.setColores(coloresSeleccionados);

	    productosService.crearProductos(productos);
		
		
	    return "Administrador/AgregarProductos.html";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/Administrador/ListaProductos")
	public String finAll(Model model) {
	    List<Productos> productos = productosService.findAll();
	    model.addAttribute("productos", productos);
	    model.addAttribute("producto", new Productos());
	    return "Administrador/ListaProductos.html";
	}
	
	@Autowired
	private ProductosRepository productosRepository;
	

	@Autowired
	private ProductosService productoService;


	@GetMapping("/DetalleProducto/{idProducto}")
	public String verDetalleProducto(@PathVariable Integer idProducto, Model model) {
	    Optional<Productos> optionalProducto = productosRepository.findById(idProducto);
	    
	    if (optionalProducto.isPresent()) {
	        Productos producto = optionalProducto.get();
	        model.addAttribute("producto", producto);
	        
	        List<Colores> coloresAsociados = productosService.obtenerColoresPorProducto(producto);
	        model.addAttribute("colores", coloresAsociados);
	    } else {

	        return "error"; 
	    }

	    return "/DetalleProducto/detalleProducto.html";
	}
	

	
	@GetMapping("/obtenerCat")
	public ResponseEntity<List<Categoria>> obtenerCategorias() {
	    List<Categoria> categorias = categoriaService.findAll();
	    return ResponseEntity.ok(categorias);
	}
	
	
	@GetMapping("/obtenerMod")
	public ResponseEntity<List<Modelo>> obtenerModelos() {
	    List<Modelo> modelos = modeloService.findAll();
	    return ResponseEntity.ok(modelos);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/modificarProducto")
	@ResponseBody
	public String modificarProducto(@RequestParam("idProducto") int idProducto,
	                                @RequestParam("descripcion") String descripcion,
	                                @RequestParam("parrafo") String parrafo,
	                                @RequestParam("precio") float precio,
	                                @RequestParam("stock") int stock,
	                                @RequestParam("imagen") String imagen,
	                                @RequestParam("activo") boolean activo,
	                                @RequestParam("categoria") int idCategoria,
	                                @RequestParam("modelo") int idModelo) {

	    // Obtener el producto existente por su ID
		Productos producto = productoService.obtenerProductoPorId(idProducto);

	    // Actualizar los campos del producto con los nuevos valores
	    producto.setDescripcion(descripcion);
	    producto.setParrafo(parrafo);
	    producto.setPrecio(precio);
	    producto.setStock(stock);
	    producto.setImagen(imagen);
	    producto.setActivo(activo);

	    // Obtener la categoría y el modelo por sus IDs
	    Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
	    Modelo modelo = modeloService.obtenerModeloPorId(idModelo);

	    // Asignar la categoría y el modelo al producto
	    producto.setCategoria(categoria);
	    producto.setModelo(modelo);

	    // Llamar al servicio para guardar los cambios en la base de datos
	    productoService.guardarProducto(producto);

	    // Devolver una respuesta (puedes ajustarla según tus necesidades)
	    return "Producto modificado exitosamente";
	}
	
	@GetMapping("/Administrador/ReporteVentas")
	public String getDetalles(Model model) {
	    try {
	        List<ListaVentasDetalle> ventasDetalles = new ArrayList<>();
	        List<Object[]> results = detalleVentaRepository.getDetalleVentaCustomData();

	        for (Object[] result : results) {
	            String nombreUsuario = (String) result[0];
	            int cantidad = (int) result[1];
	            double total = (double) result[2];
	            String productoDescripcion = (String) result[3];
	            Timestamp fechaVentaTimestamp = (Timestamp) result[4]; // Obtener el timestamp

	            // Convertir el Timestamp a LocalDateTime
	            LocalDateTime fechaVentaLocalDateTime = fechaVentaTimestamp.toLocalDateTime();
	            ListaVentasDetalle ventaDetalle = new ListaVentasDetalle();
	            ventaDetalle.setNombreUsuario(nombreUsuario);
	            ventaDetalle.setProducto(productoDescripcion);
	            ventaDetalle.setCantidad(cantidad);
	            ventaDetalle.setTotal(total);

	            ventaDetalle.setFechaVenta(fechaVentaLocalDateTime);
	            
	            ventasDetalles.add(ventaDetalle);
	        }

	        model.addAttribute("ventasDetalles", ventasDetalles);
	        return "/Administrador/ListaVentas.html"; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error"; 
	    }
	}
	
	
	
	@DeleteMapping("/Administrador/EliminarProducto")
	public ResponseEntity<String> eliminarProducto(@RequestParam("idProducto") int idProducto) {
	    try {
	        productoService.deleteProductos(idProducto);
	        return ResponseEntity.ok("Producto eliminado exitosamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
	    }
	}
}
