package com.everis.beca.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class EntradaOutputDTO {

	private Long id;
	private String placa;
	private String cor;
	private String modelo;
	private String tipoVeiculo;
	private BigDecimal precoPorTipoVeiculo;
	private String cpf;
	private String nome;
	private String telefone;
	private String celular;
	private String email;
	private OffsetDateTime dataHoraEntrada; // "2020-11-05T16:09:04.635106-03:00"

	private OffsetDateTime dataHoraSaida; // "2020-11-05T16:09:04.620148-03:00"
	private BigDecimal valorPagamento;
	private BigDecimal valorTotalEntrada;
	private Long tempoEstacionamento;

	public OffsetDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(OffsetDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
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

	public Long getTempoEstacionamento() {
		return tempoEstacionamento;
	}

	public void setTempoEstacionamento(Long tempoEstacionamento) {
		this.tempoEstacionamento = tempoEstacionamento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public BigDecimal getPrecoPorTipoVeiculo() {
		return precoPorTipoVeiculo;
	}

	public void setPrecoPorTipoVeiculo(BigDecimal precoPorTipoVeiculo) {
		this.precoPorTipoVeiculo = precoPorTipoVeiculo;
	}

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

	public OffsetDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(OffsetDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}
}
