package com.everis.beca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.beca.domain.model.Pagamento;
import com.everis.beca.domain.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping("/consulta/{idEntrada}")
	public Pagamento consultarValorEstacionamento(@PathVariable Long idEntrada) {
		return pagamentoService.consultarValor(idEntrada);
	}
	
}
