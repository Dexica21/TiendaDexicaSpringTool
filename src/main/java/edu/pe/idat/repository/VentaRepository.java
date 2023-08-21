package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pe.idat.Model.DetalleVenta;
import edu.pe.idat.Model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer>{
	
	
}
