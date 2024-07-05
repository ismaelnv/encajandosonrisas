package com.example.demo.Modelos.DTO;

public class ClienteDto {
	
	private int cod_cli;
	private String nom_cli;
	
	public ClienteDto() {
		super();
	}

	public ClienteDto(int cod_cli, String nom_cli) {
		super();
		this.cod_cli = cod_cli;
		this.nom_cli = nom_cli;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String getNom_cli() {
		return nom_cli;
	}

	public void setNom_cli(String nom_cli) {
		this.nom_cli = nom_cli;
	}
	
	
}
