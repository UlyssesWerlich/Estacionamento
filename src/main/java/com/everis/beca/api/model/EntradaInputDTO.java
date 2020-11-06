package com.everis.beca.api.model;

import javax.validation.constraints.NotNull;

public class EntradaInputDTO {
	
	@NotNull
	private Long veiculoId;
	
	@NotNull
	private Long clienteId;

	public Long getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
}
