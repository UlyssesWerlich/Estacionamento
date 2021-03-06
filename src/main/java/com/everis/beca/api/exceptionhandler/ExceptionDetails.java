package com.everis.beca.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

public class ExceptionDetails {

	private String titulo;
	private Integer status;
	private OffsetDateTime dataHora;
	private List<String> mensagens;

	public ExceptionDetails(String titulo, Integer status) {
		super();
		this.titulo = titulo;
		this.status = status;
		this.dataHora = OffsetDateTime.now();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

}
