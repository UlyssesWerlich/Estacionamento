package com.everis.beca.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long entradaId;
	
	@NotNull
	private BigDecimal valorPagamento;

	private BigDecimal valorTotalEntrada;
	
	private Long quantidadeIntervaloDeTempo;
	
	private Long tempoEstacionamento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntradaId() {
		return entradaId;
	}

	public void setEntradaId(Long entradaId) {
		this.entradaId = entradaId;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public BigDecimal getValorTotalEntrada() {
		return valorTotalEntrada;
	}

	public void setValorTotalEntrada(BigDecimal valorTotalEntrada) {
		this.valorTotalEntrada = valorTotalEntrada;
	}

	public Long getQuantidadeIntervaloDeTempo() {
		return quantidadeIntervaloDeTempo;
	}

	public void setQuantidadeIntervaloDeTempo(Long quantidadeIntervaloDeTempo) {
		this.quantidadeIntervaloDeTempo = quantidadeIntervaloDeTempo;
	}

	public Long getTempoEstacionamento() {
		return tempoEstacionamento;
	}

	public void setTempoEstacionamento(Long tempoEstacionamento) {
		this.tempoEstacionamento = tempoEstacionamento;
	}
}