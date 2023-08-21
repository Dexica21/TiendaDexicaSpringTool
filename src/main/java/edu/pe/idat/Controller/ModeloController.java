package edu.pe.idat.Controller;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pe.idat.Model.Modelo;
import edu.pe.idat.Model.Usuario;
import edu.pe.idat.service.ModeloService;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ModeloController {


	@GetMapping("/Administrador/Web")
	public String Administracion(Model model) {
		
		return "Administrador/AdministradorModerno.html";
	}
	

	@GetMapping("/Administrador/AgregarModelo")
	public String Mod(Model model) {
		model.addAttribute("modelo", new Modelo());
		return "Administrador/AgregarModelo.html";
	}
	
	@Autowired
	private ModeloService modeloService;

	@PostMapping("/Administrador/AgregarModelo")
	public String BotonFormulario(@ModelAttribute("modelo") Modelo modelo) {
		
	    modeloService.CrearModelo(modelo);
	    return "Administrador/AgregarModelo.html";
	}
	

	@RequestMapping(value = "/Administrador/modificarmodelo", method = RequestMethod.POST)
    @ResponseBody
    public String sendPostMessage(
    		@RequestParam("idModelo") String idModelo,
    		@RequestParam("descripcion")String descripcion,
            @RequestParam("Activo") String activo){
{

	//creamos un nuevo objeto usuario y llamamos al servicio de actualizar usuario
        Modelo modelo = new Modelo();
        modelo.setIdModelo(Integer.parseInt(idModelo));
        modelo.setDescripcion(descripcion);
        modelo.setActivo(Boolean.parseBoolean(activo));

        modeloService.ActualizarModelo(modelo);

        return "redirect:Administrador/ListaModelos.html";
        
        }
    }

	@GetMapping("/Administrador/ListaModelos")
	public String finAll(Model model) {
	    List<Modelo> modelos = modeloService.findAll();
	    model.addAttribute("modelos", modelos);
	    model.addAttribute("modelo", new Modelo());
	    return "Administrador/ListaModelos.html";
	}
	
	
	@DeleteMapping("/Administrador/EliminarModelo")
	public ResponseEntity<String> eliminarModelo(@RequestParam("idModelo") int idModelo) {
	    try {
	        modeloService.deleteModelo(idModelo);
	        return ResponseEntity.ok("Modelo eliminado exitosamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el modelo");
	    }
	}
	
	
	@GetMapping("/Administrador/Reporte")
	public String ReporteVentas(Model model) {
		
		return "Administrador/ListaVentas.html"; 
	}
		
	
}
