package edu.pe.idat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import edu.pe.idat.Model.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
    Usuario findByNombreUsuarioAndPass(@Param("username") String username, @Param("password") String password);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_crear_usuario(:username, :correo, :password, :fecha_registro)}",
	nativeQuery = true)
	void crearUsuario(@Param("username")String username,
			@Param("correo")String correo,
			@Param("password")String password,
			@Param("fecha_registro")LocalDateTime fecha_registro
			);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Optional<Usuario> findByUsername(@Param("username") String username);	
	
	@Modifying
    @Query("UPDATE Usuario u SET u.username = :username, u.correo = :correo WHERE u.idUsuario = :idUsuario")
    void updateUsuario(@Param("idUsuario") int idUsuario, @Param("username") String username, @Param("correo") String correo);
	
	
	@Modifying
    @Query("UPDATE Usuario u SET u.persona = :persona WHERE u.idUsuario = :idUsuario")
    void updateIdPersona(@Param("idUsuario") int idUsuario, @Param("persona") Persona persona);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_actualizar_usuario(:id_Usuario, :password, :correo, :Activo)}",nativeQuery = true)
	void actualizarUsuario(
			@Param("id_Usuario")Integer idUsuario,
			@Param("correo")String correo,
			@Param("password")String password,
			@Param("Activo")boolean activo)
	;
	

	//Lista mediante un query para consultar los usuarios registrados
	@Query("SELECT u FROM Usuario u ORDER BY u.username")
	List<Usuario> findAllOrderedByNombreUsuario();
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	Usuario findByNombreUsuario(String username);
	
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario obtenerUsuarioPorNombre(
    		@Param("username") String username)
    ;
	
	@Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.username = ?1")
    boolean existeUsername(String username);
	
	@Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.correo = ?1")
    boolean existeCorreo(String correo);
}





