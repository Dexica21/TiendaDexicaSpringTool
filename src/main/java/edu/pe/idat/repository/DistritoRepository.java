package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pe.idat.Model.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer>{

	@Query("SELECT d FROM Distrito d ORDER BY d.descripcion")
	List<Distrito>obtenerDistritos();
}
