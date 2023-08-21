package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.DetalleVenta;
import edu.pe.idat.Model.DetalleVentaRequest;
import edu.pe.idat.Model.Venta;
import edu.pe.idat.Model.VentaRequest;
import edu.pe.idat.repository.DetalleVentaRepository;
import edu.pe.idat.repository.VentaRepository;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import edu.pe.idat.Model.*;


@Service
public class VentaService {
	
	@Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    
    
    
}
