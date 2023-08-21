package edu.pe.idat.Model;

import java.util.List;
import java.util.ArrayList;

public class VentaRequest {

	private Usuario usuario;
	private Distrito distrito;
	private String direccion;
	private int CantidadProducto;
	private double MontoTotal;
	private List<DetalleVentaRequest> detalles;


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


	public List<DetalleVentaRequest> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<DetalleVentaRequest> detalles) {
		this.detalles = detalles;
	}
	
	public VentaRequest() {
        detalles = new ArrayList<>();
    }
	
	
	
	
}
