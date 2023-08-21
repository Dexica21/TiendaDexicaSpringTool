package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Modelo;
import edu.pe.idat.repository.ModeloRepository;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	public void CrearModelo(Modelo modelo) {
		modeloRepository.CrearModelo(modelo.getDescripcion());
	}
	
	public void ActualizarModelo(Modelo modelo) {
		modeloRepository.actualizarModelo(modelo.getIdModelo(),modelo.getDescripcion() ,modelo.isActivo());
	}
	
	public List<Modelo> findAll(){
		return modeloRepository.ObtenerLosModelos();
	}
	
	public void deleteModelo(Integer idModelo) {
        modeloRepository.deleteModeloById(idModelo);
    }
	
	public Modelo obtenerModeloPorId(int IdModelo) {
		
		return modeloRepository.obtenerModeloPorId(IdModelo);
	}
}
