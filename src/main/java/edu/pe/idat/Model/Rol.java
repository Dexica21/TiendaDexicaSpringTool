package edu.pe.idat.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrol")
	private Integer idrol;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERol nombre;
	
	public Rol() {
		
	}

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public ERol getNombre() {
		return nombre;
	}

	public void setNombre(ERol nombre) {
		this.nombre = nombre;
	}
	
	public Rol(ERol nombre) {
        this.nombre = nombre;
    }
	
}
