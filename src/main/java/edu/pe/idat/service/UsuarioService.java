package edu.pe.idat.service;

import edu.pe.idat.Model.*;
import edu.pe.idat.repository.*;
import jakarta.transaction.Transactional;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;


	@Transactional
	public void createUser(Usuario usuario) {
	    String pass_encriptada = encriptarPassword(usuario.getPassword());
	    LocalDateTime fechaRegistro = LocalDateTime.ofInstant(usuario.getFecha_registro().toInstant(), ZoneId.systemDefault());
	    usuarioRepository.crearUsuario(usuario.getUsername(), usuario.getCorreo(), pass_encriptada, fechaRegistro);
	}
	
	public List<Usuario> findAll(){
	    return usuarioRepository.findAllOrderedByNombreUsuario();
	}
	
	public void actualizarIdPersona(int idUsuario, Persona persona) {
	    usuarioRepository.updateIdPersona(idUsuario, persona);
	}
	
	@Transactional
	public boolean validarCredenciales(String username, String password) {
	    Usuario usuario = usuarioRepository.findByNombreUsuario(username);

	    if (usuario != null) {
	        String hashedPassword = usuario.getPassword();

	        // Verificar que la contraseña ingresada coincida con el hash almacenado
	        if (BCrypt.checkpw(password, hashedPassword)) {
	            return true; // Credenciales válidas
	        }
	    }

	    return false; // Credenciales inválidas
	}
	
	public void ActualizarUsuario(Usuario usuario) {
		String pass_encriptada = encriptarPassword(usuario.getPassword());
	    usuario.setPassword(pass_encriptada);
        usuarioRepository.actualizarUsuario(usuario.getIdUsuario(), usuario.getCorreo(), usuario.getPassword(),
                usuario.isActivo());
    }
	
	private String encriptarPassword(String password) {
	    String salt = BCrypt.gensalt();
	    String pass_encriptada = BCrypt.hashpw(password, salt);
	    return pass_encriptada;
	}
	
	public Usuario obtenerUsuarioPorId(int idUsuario) {
	    return usuarioRepository.findById(idUsuario).orElse(null);
	}
	
	public Usuario obtenerUsuarioPorNombre(String username) {
		return usuarioRepository.obtenerUsuarioPorNombre(username);
	}
	
	public boolean existeNombreUsuario(String username) {
        return usuarioRepository.existeUsername(username);
    }
	
	public boolean existeCorreo(String correo) {
        return usuarioRepository.existeCorreo(correo);
    }
	
	public Usuario findbyUsername(String username) {
		Optional<Usuario> optional = usuarioRepository.findByUsername(username);
		return optional.get();
	}
	
	@Transactional
    public void actualizarUsuario(int idUsuario, String nuevoUsername, String nuevoCorreo) {
        usuarioRepository.updateUsuario(idUsuario, nuevoUsername, nuevoCorreo);
    }
	
}
