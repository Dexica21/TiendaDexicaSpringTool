package edu.pe.idat.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpersona")
	private int idpersona;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellidopat")
	private String apellidopat;
	
	@Column(name = "apellidomat")
	private String apellidomat;
	
	@Column(name = "documentoidentidad")
	private long documentoidentidad;

	public int getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidopat() {
		return apellidopat;
	}

	public void setApellidopat(String apellidopat) {
		this.apellidopat = apellidopat;
	}

	public String getApellidomat() {
		return apellidomat;
	}

	public void setApellidomat(String apellidomat) {
		this.apellidomat = apellidomat;
	}

	public long getDocumentoidentidad() {
		return documentoidentidad;
	}

	public void setDocumentoidentidad(long documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	
	
	
}
