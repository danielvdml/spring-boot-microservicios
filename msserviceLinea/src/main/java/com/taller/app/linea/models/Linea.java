package com.taller.app.linea.models;

public class Linea {
	private Articulo articulo;
	private Integer cantidad;

	public Linea() {
		// TODO Auto-generated constructor stub
	}

	public Linea(Articulo articulo, Integer cantidad) {
	
		this.articulo = articulo;
		this.cantidad = cantidad;
	}



	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {

		return articulo.getPrecio() * cantidad.doubleValue();
	}

}
