package edu.pe.idat.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Modelo;
import edu.pe.idat.Model.Usuario;
import edu.pe.idat.service.CategoriaService;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CategoriaController {


	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/Administrador/Categoria")
	public String CategoriaInicio(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		return "Administrador/AgregarCategoria.html";
	}
	

	@PostMapping("/Administrador/AgregarCategoria")
	public String BotonFormulario(@ModelAttribute("categoria") Categoria categoria) {
		
	    categoriaService.CrearCategoria(categoria);
	    return "Administrador/AgregarCategoria.html";
	}
	
	
	
	//aca utilizamos un metodo Post para actualizar la categoria
		@RequestMapping(value = "/modificarcategoria", method = RequestMethod.POST)
	    @ResponseBody
	    public String sendPostMessage(
	    		@RequestParam("idCategoria") String idCategoria,
	    		@RequestParam("descripcion")String descripcion,
	            @RequestParam("Activo") String activo){
	{

		//creamos un nuevo objeto categoria y llamamos al servicio de actualizar categoria
	        Categoria categoria = new Categoria();
	        categoria.setIdCategoria(Integer.parseInt(idCategoria));
	        categoria.setDescripcion(descripcion);
	        categoria.setActivo(Boolean.parseBoolean(activo));

	        categoriaService.ActualizarCategoria(categoria);

	        return "redirect:Administrador/ListaCategorias.html";
	        
	        }
	    }
	

	@GetMapping("/Administrador/ListaCategoria")
	public String finAll(Model model) {
	    List<Categoria> categorias = categoriaService.findAll();
	    model.addAttribute("categorias", categorias);
	    model.addAttribute("categoria", new Categoria());
	    return "Administrador/ListaCategorias.html";
	}
	
	
	@DeleteMapping("/Administrador/EliminarCategoria")
	public ResponseEntity<String>eliminarCategoria(@RequestParam("idCategoria") int idCategoria){
		try {
			categoriaService.deleteCategoria(idCategoria);
			return ResponseEntity.ok("Categoria registrada exitosamente");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la categoria");
		}
	}
	
}
