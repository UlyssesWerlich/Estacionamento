package com.everis.beca.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PagamentoInputDTO {

	@NotNull
	@Min(0)
	private BigDecimal valorPagamento;

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
}
