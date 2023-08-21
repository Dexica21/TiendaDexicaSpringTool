package edu.pe.idat.Model;

import java.util.List;

public class DetalleVentaRequest {
	private Productos producto;
	private int cantidad;
	private double Total;

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
		return Total;
	}
	public void setTotal(double total) {
		Total = total;
	}
	

	
	
	
}
