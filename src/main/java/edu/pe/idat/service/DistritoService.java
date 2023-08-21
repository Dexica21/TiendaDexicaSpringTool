package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.Distrito;
import edu.pe.idat.repository.DistritoRepository;

@Service
public class DistritoService {

	@Autowired
	private DistritoRepository distritoRepository;
	
	public List<Distrito> findAll(){
		return distritoRepository.findAll();
	}
}
