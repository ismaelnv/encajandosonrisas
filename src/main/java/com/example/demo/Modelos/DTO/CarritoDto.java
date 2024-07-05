package com.example.demo.Modelos.DTO;


public class CarritoDto {
	
	private int cod_carrito;
	private int cantidad;
	
	
	
	public CarritoDto() {
		super();
	}
	

	public CarritoDto(int cod_carrito, int cantidad) {
		super();
		this.cod_carrito = cod_carrito;
		this.cantidad = cantidad;
		
	}

	public int getCod_carrito() {
		return cod_carrito;
	}

	public void setCod_carrito(int cod_carrito) {
		this.cod_carrito = cod_carrito;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	


}
