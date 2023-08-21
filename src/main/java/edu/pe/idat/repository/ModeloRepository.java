package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Modelo;
import jakarta.transaction.Transactional;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

	@Transactional
	@Modifying
	@Query(value="{call sp_crear_Modelo(:descripcion)}",nativeQuery = true)
	void CrearModelo(
			@Param("descripcion")String descripcion);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_actualizar_modelo(:id_Modelo, :descripcion, :Activo)}",nativeQuery = true)
	void actualizarModelo(
			@Param("id_Modelo")Integer idModelo,
			@Param("descripcion")String descripcion,
			@Param("Activo")boolean activo)
	;
	
	@Query("SELECT m FROM Modelo m ORDER BY m.descripcion")
	List<Modelo> ObtenerLosModelos();
	
	@Transactional
	@Modifying
    @Query("DELETE FROM Modelo m WHERE m.id = :id_Modelo")
    void deleteModeloById(@Param("id_Modelo") Integer idModelo);
	
	
	@Query("SELECT m FROM Modelo m WHERE m.idModelo = :idModelo")
    Modelo obtenerModeloPorId(@Param("idModelo") int idModelo);
}
