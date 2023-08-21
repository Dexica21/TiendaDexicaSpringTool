package edu.pe.idat.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;
import java.util.*;
import edu.pe.idat.repository.*;

import edu.pe.idat.Model.*;
import edu.pe.idat.service.*;


@Controller
public class HomeController {
	


	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/Dexica/home")
	public String home(Model model, @RequestParam(name = "usuario", required=false)String username) {
		model.addAttribute("username",username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAuthenticated = authentication.isAuthenticated();
		
		if (authentication.isAuthenticated()) {
			String nombreUsuarioAutenticado = authentication.getName();
			model.addAttribute("nombreUsuarioAutenticado", nombreUsuarioAutenticado);
		}
		return "home.html";
	}
	
	@GetMapping("/Web/Dexica/Registro")
	public String Registro(Model model) {
		
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);
		
		return "Registro.html";
	}
	
	
	
	
	@Autowired
	private ProductosService productosService;
	
	@GetMapping("/Producto/Catalogo")
	public String CatalogoProductos(Model model,@RequestParam(value = "usuario", required = false) String username) {
		model.addAttribute("nombreUsuario", username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAuthenticated = authentication.isAuthenticated();
		
		if (authentication.isAuthenticated()) {
			String nombreUsuarioAutenticado = authentication.getName();
			model.addAttribute("nombreUsuarioAutenticado", nombreUsuarioAutenticado);
		}
		List<Productos>ProductosList = productosService.findAll();
		 model.addAttribute("productos", ProductosList);
		return "Catalogo.html";
	}
	


	
	@GetMapping("/Web/Dexica/Login")
	public String Login(Model model) {
			
		model.addAttribute("log", new Usuario());
		
		return "Login";
	}
	
	@GetMapping("/Web/Dexica/OlvidasteContraseña")
	public String OlvidasteContraseña(Model model) {
		
		return "OlvidasteContraseña.html";
	}
	
	
	
	//Para registrar un nuevo cliente 
	@PostMapping("/Web/Dexica/Registrar")
	public String BotonFormulario(@ModelAttribute("usuario") Usuario usuario) {
	    // Verificar si los campos requeridos están llenos
	    if (usuario.getUsername() == null || usuario.getUsername().isEmpty() ||
	        usuario.getCorreo() == null || usuario.getCorreo().isEmpty() ||
	        usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        return "redirect:/Web/Dexica/Registro?error=Por%20favor,%20complete%20todos%20los%20campos.";
	    }

	    // Verificar si el nombre de usuario ya está en uso
	    if (usuarioService.existeNombreUsuario(usuario.getUsername())) {
	        return "redirect:/Web/Dexica/Registro?error=El%20nombre%20de%20usuario%20ya%20est%C3%A1%20en%20uso.";
	    }

	    // Verificar si el correo ya está en uso
	    if (usuarioService.existeCorreo(usuario.getCorreo())) {
	        return "redirect:/Web/Dexica/Registro?error=El%20correo%20ya%20est%C3%A1%20en%20uso.";
	    }

	    // Registro exitoso, asignar el rol de "USER" al nuevo usuario
	    Set<Rol> roles = new HashSet<>();
	    Rol rolUser = new Rol();
	    rolUser.setNombre(ERol.USER); // Aquí asignamos el rol "USER" al nuevo objeto Rol
	    roles.add(rolUser);
	    usuario.setRol(roles);

	    // Establecer la fecha de registro
	    usuario.setFecha_registro(new Date());

	    // Guardar el usuario en la base de datos
	    usuarioService.createUser(usuario);

	    return "redirect:/Web/Dexica/Registro?success=true";
	}
	
	
	
	
	
	//aca utilizamos un metodo Post para actualizar el Usuario
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public String sendPostMessage(
    		@RequestParam("idUsuario") String idUsuario,
            @RequestParam("password") String password,
            @RequestParam("correo") String correo,
            @RequestParam("Activo") String activo){
{

	//creamos un nuevo objeto usuario y llamamos al servicio de actualizar usuario
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(idUsuario));
        usuario.setPassword(password);
        usuario.setCorreo(correo);
        usuario.setActivo(Boolean.parseBoolean(activo));

        if (!password.isEmpty()) {
            String pass_encriptada = DigestUtils.sha256Hex(password);
            usuario.setPassword(pass_encriptada);
        } else {
            // Mantener la contraseña existente si no se proporciona una nueva
            Usuario usuarioExistente = usuarioService.obtenerUsuarioPorId(Integer.parseInt(idUsuario));
            usuario.setPassword(usuarioExistente.getPassword());
        }
        
        usuarioService.ActualizarUsuario(usuario);

        return "redirect:/Administracion.html";
        
        }
    }

	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/Administrador/ListaClientes")
	public String finAll(Model model) {
	    List<Usuario> usuarios = usuarioService.findAll();
	    model.addAttribute("usuarios", usuarios);
	    model.addAttribute("usuario", new Usuario());

	    return "Administrador/ListaClientes.html";
	}
	

	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/Administrador/Registro")
	public String RegistroAdmin(Model model) {
		
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);
		
		return "Administrador/AgregarAdministrador.html";
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/Administrador/CrearCuenta")
	public String BotonFr(@ModelAttribute("usuario") Usuario usuario) {
		
		if (usuario.getUsername() == null || usuario.getUsername().isEmpty() ||
		        usuario.getCorreo() == null || usuario.getCorreo().isEmpty() ||
		        usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
		        return "redirect:/Administrador/Registro?error=Por%20favor,%20complete%20todos%20los%20campos.";
		    }

		    // Verificar si el nombre de usuario ya está en uso
		    if (usuarioService.existeNombreUsuario(usuario.getUsername())) {
		        return "redirect:/Administrador/Registro?error=El%20nombre%20de%20usuario%20ya%20est%C3%A1%20en%20uso.";
		    }

		    // Verificar si el correo ya está en uso
		    if (usuarioService.existeCorreo(usuario.getCorreo())) {
		        return "redirect:/Administrador/Registro?error=El%20correo%20ya%20est%C3%A1%20en%20uso.";
		    }


		    Set<Rol> rol = new HashSet<>();
		    Rol rolAdmin = new Rol();
		    rolAdmin.setNombre(ERol.ADMIN); 
		    rol.add(rolAdmin);
		    usuario.setRol(rol);

		    System.out.println("Rol asignado: " + rolAdmin.getNombre());
		    // Establecer la fecha de registro
		    usuario.setFecha_registro(new Date());

		    // Guardar el usuario en la base de datos
		    usuarioService.createUser(usuario);

		    return "Administrador/AgregarAdministrador.html";
	}
	
	
	
}


