package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Productos;
import edu.pe.idat.Model.Usuario;
import jakarta.transaction.Transactional;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	@Transactional
	@Modifying
	@Query(value="{call sp_crear_categoria(:descripcion)}",nativeQuery = true)
	void CrearCategoria(
			@Param("descripcion")String descripcion);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_actualizar_categoria(:id_Categoria, :descripcion, :Activo)}",nativeQuery = true)
	void actualizarCategoria(
			@Param("id_Categoria")Integer idCategoria,
			@Param("descripcion")String descripcion,
			@Param("Activo")boolean activo)
	;

	@Query("SELECT c FROM Categoria c ORDER BY c.descripcion")
	List<Categoria> ObtenerLasCategorias();
	
	@Transactional
	@Modifying
    @Query("DELETE FROM Categoria c WHERE c.id = :id_Categoria")
    void deleteCategoriaById(@Param("id_Categoria") Integer idCategoria);
	
	@Query("SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    Categoria obtenerCategoriaPorId(@Param("idCategoria") int idCategoria);
	}
