package edu.pe.idat.Model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "colores")
public class Colores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Colores")
	private int idColores;
	
	@Column(name="descripcion")
	private String descripcion;

	@ManyToMany(mappedBy = "colores") // La propiedad "colores" debe coincidir con el nombre del campo en la entidad Productos
    private Set<Productos> productos;
	
	public int getIdColores() {
		return idColores;
	}

	public void setIdColores(int idColores) {
		this.idColores = idColores;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Set<Productos> getProductos() {
        return productos;
    }

    public void setProductos(Set<Productos> productos) {
        this.productos = productos;
    }
	
	
}
