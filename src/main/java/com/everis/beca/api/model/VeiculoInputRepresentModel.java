package com.everis.beca.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VeiculoInputRepresentModel {
	
	@NotBlank
	private String placa;
	
	@NotBlank
	private String cor;
	
	@NotBlank
	private String modelo;
	
	@NotNull
	private Long tipoVeiculoId;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getTipoVeiculoId() {
		return tipoVeiculoId;
	}

	public void setTipoVeiculoId(Long tipoVeiculoId) {
		this.tipoVeiculoId = tipoVeiculoId;
	}
	
}
