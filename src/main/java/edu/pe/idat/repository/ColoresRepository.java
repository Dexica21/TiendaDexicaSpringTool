package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pe.idat.Model.Colores;
import edu.pe.idat.Model.Productos;
import jakarta.transaction.Transactional;

public interface ColoresRepository extends JpaRepository<Colores, Integer>{

	@Transactional
	@Modifying
	@Query(value="{call sp_crear_Colores(:descripcion)}",nativeQuery = true)
	void CrearColores(
			@Param("descripcion")String descripcion);
	
	@Query("SELECT cr FROM Colores cr ORDER BY cr.descripcion")
	List<Colores>ObtenerlosColores();
	
	List<Colores> findByProductos(Productos producto);
}
