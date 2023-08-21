package edu.pe.idat.service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.Colores;
import edu.pe.idat.repository.ColoresRepository;

@Service
public class ColoresService {

	@Autowired
	private ColoresRepository coloresRepository;
	
	public void CrearColores(Colores colores) {
		coloresRepository.CrearColores(colores.getDescripcion());
	}
	
	public List<Colores>findAll(){
		return coloresRepository.ObtenerlosColores();
	}
	
	public Set<Colores> obtenerColoresPorIds(List<Integer> colorIds) {
        List<Colores> colores = coloresRepository.findAllById(colorIds);
        return new HashSet<>(colores);
	}
}
