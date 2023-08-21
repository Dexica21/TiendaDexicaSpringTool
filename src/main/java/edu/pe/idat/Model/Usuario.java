package edu.pe.idat.Model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Usuario")
	private int idUsuario;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "correo")
	private String correo;	
	
	@Column(name = "password")
	private String password;
    
	@Column(name = "fecha_registro")
	private Date fecha_registro;
	
	@Column(name = "Activo")
	private boolean activo;
	
	@JoinColumn(name = "idpersona")
	@OneToOne
	private Persona persona;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_rol",
				joinColumns = @JoinColumn(name = "id_Usuario"),
				inverseJoinColumns = @JoinColumn(name = "idrol"))
	private Set<Rol>rol = new HashSet<>();

	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Set<Rol> getRol() {
		return rol;
	}

	public void setRol(Set<Rol> rol) {
		this.rol = rol;
	}
	

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Usuario(Persona persona) {
        this.persona = persona;
        
    }
	
	public Usuario() {
		
	}
	
}
