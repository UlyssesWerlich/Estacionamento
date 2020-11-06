package com.everis.beca.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteInputDTO {
	
	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;

	@NotBlank
	@Size(max = 80)
	private String nome;

	@NotBlank
	@Size(min = 6, max = 20)
	private String telefone;

	@Size(max = 20)
	private String celular;

	@Email
	@Size(max = 255)
	private String email;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
