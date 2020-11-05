package com.everis.beca.api.model;

import javax.validation.constraints.NotBlank;

public class ClienteNome {
	
	@NotBlank
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
