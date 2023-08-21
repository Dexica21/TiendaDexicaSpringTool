package edu.pe.idat.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_DetalleVentas")
	private int idDetalleVenta;
	
	@ManyToOne
    @JoinColumn(name = "id_Venta")
    private Venta venta;
	
	@ManyToOne
    @JoinColumn(name = "id_Producto")
    private Productos producto;
	
	@Column(name ="cantidad")
	private int cantidad;
	
	@Column(name ="Total")
    private double total;
	
	@Column(name="fecha_venta")
	private LocalDateTime fechaVenta;

	public int getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
	
}
