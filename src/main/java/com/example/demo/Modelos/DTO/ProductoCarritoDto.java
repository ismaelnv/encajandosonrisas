package com.example.demo.Modelos.DTO;

public class ProductoCarritoDto {
	
	
	private Integer cod_carrito;
	private Integer cod_producto;
	private Integer cantidad;
	
	public ProductoCarritoDto() {
		super();
	}

	public ProductoCarritoDto( Integer cod_carrito, Integer cod_producto,
			Integer cantidad) {
		super();

		this.cod_carrito = cod_carrito;
		this.cod_producto = cod_producto;
		this.cantidad = cantidad;
	}


	public Integer getCod_carrito() {
		return cod_carrito;
	}

	public void setCod_carrito(Integer cod_carrito) {
		this.cod_carrito = cod_carrito;
	}

	public Integer getCod_producto() {
		return cod_producto;
	}

	public void setCod_producto(Integer cod_producto) {
		this.cod_producto = cod_producto;
	}


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	

}
