package com.everis.beca.api.exceptionhandler;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.everis.beca.domain.exception.RegraDeNegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RegraDeNegocioException.class)
	public ResponseEntity<Object> handleRegraDeNegocioException(RegraDeNegocioException ex, WebRequest request) { 
		var status = HttpStatus.BAD_REQUEST;
		var details = new ExceptionDetails(ex.getMessage(), status.value());
		
		return handleExceptionInternal(ex, details, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String titulo = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
		var details = new ExceptionDetails(titulo, status.value());
		var mensagens = new ArrayList<String>();
	
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			mensagens.add(nome + " " + mensagem);
		}
		
		details.setMensagens(mensagens);
		return super.handleExceptionInternal(ex, details, headers, status, request);
	}
	
}
