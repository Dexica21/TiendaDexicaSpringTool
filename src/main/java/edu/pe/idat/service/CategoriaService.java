package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Productos;
import edu.pe.idat.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void CrearCategoria(Categoria categoria) {
		categoriaRepository.CrearCategoria(categoria.getDescripcion());
	}
	
	public void ActualizarCategoria(Categoria categoria) {
		categoriaRepository.actualizarCategoria(categoria.getIdCategoria(), categoria.getDescripcion(), categoria.isActivo());
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.ObtenerLasCategorias();
	}

	public void deleteCategoria(Integer idCategoria) {
        categoriaRepository.deleteCategoriaById(idCategoria);
    }
	
	public Categoria obtenerCategoriaPorId(int IdCategoria) {
		
		return categoriaRepository.obtenerCategoriaPorId(IdCategoria);
	}
	
}
