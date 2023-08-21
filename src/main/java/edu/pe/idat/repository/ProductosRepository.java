package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.Model.Categoria;
import edu.pe.idat.Model.Colores;
import edu.pe.idat.Model.Productos;
import edu.pe.idat.Model.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos,Integer>{

	@Transactional
	@Modifying
	@Query(value= "{call sp_crear_productos(:descripcion, :parrafo,:precio, :stock, :imagen, :idCategoria, :idModelo, :colores)}", nativeQuery = true)
	void CrearProductos(
			@Param("descripcion")String descripcion,
			@Param("parrafo")String parrafo,
			@Param("precio")float precio,
			@Param("stock")int stock,
			@Param("imagen")String imagen,
			@Param("idCategoria")int categoria,
			@Param("idModelo")int modelo,
			@Param("colores") String colores
			);
	
	@Query("SELECT p FROM Productos p ORDER BY p.descripcion")
	List<Productos> ObtenerLosProductos();
	
	@Transactional
	@Modifying
    @Query("DELETE FROM Productos p WHERE p.id = :id_Producto")
    void deleteProductoById(@Param("id_Producto") Integer idProducto);
	
	@Query("SELECT p FROM Productos p WHERE p.idProducto = :idProducto")
    Productos obtenerProductoPorId(@Param("idProducto") int idProducto);
	
	
}
