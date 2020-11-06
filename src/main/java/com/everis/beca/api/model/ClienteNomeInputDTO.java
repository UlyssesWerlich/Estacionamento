package com.everis.beca.api.model;

import javax.validation.constraints.NotBlank;

public class ClienteNomeInputDTO {
	
	@NotBlank
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
