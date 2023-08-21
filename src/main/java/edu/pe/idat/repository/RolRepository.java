package edu.pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import edu.pe.idat.Model.Rol;
import edu.pe.idat.Model.ERol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	@Query("SELECT r FROM Rol r WHERE r.nombre = :nombre")
    Optional<Rol> findByNombre(@Param("nombre") ERol nombre);

}
