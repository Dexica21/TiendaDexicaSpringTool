package edu.pe.idat.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Venta")
	private int idVenta;
	
	@ManyToOne
	@JoinColumn(name = "id_Usuario")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "id_Distrito")
    private Distrito distrito;
	
	@Column(name = "direccion")
	private String direccion;

	@Column(name = "CantidadProducto")
	private int CantidadProducto;
	
	@Column(name = "MontoTotal")
	private double MontoTotal;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<DetalleVenta> detalles;
	
	@Column(name = "fecha_venta")
	private LocalDateTime fechaVenta;

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCantidadProducto() {
		return CantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		CantidadProducto = cantidadProducto;
	}

	public double getMontoTotal() {
		return MontoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		MontoTotal = montoTotal;
	}

	public List<DetalleVenta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}



	
}
