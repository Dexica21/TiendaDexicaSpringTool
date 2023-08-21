package edu.pe.idat.Model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Productos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Producto")
	private int idProducto;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="parrafo")
	private String parrafo;
	
	@Column(name="precio")
	private float precio;
	
	@Column(name="stock")
	private int stock;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="Activo")
	private boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "id_Categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_Modelo")
	private Modelo modelo;
	
	@ManyToMany
    @JoinTable(
        name = "productocolores",
        joinColumns = @JoinColumn(name = "id_Producto"),
        inverseJoinColumns = @JoinColumn(name = "id_Colores")
    )
    private Set<Colores> colores;
	

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getParrafo() {
		return parrafo;
	}

	public void setParrafo(String parrafo) {
		this.parrafo = parrafo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Set<Colores> getColores() {
		return colores;
	}

	public void setColores(Set<Colores> colores) {
		this.colores = colores;
	}	
}
