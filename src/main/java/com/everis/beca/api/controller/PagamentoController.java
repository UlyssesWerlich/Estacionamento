package com.everis.beca.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.everis.beca.api.model.PagamentoInputDTO;
import com.everis.beca.api.utils.PagamentoModelMapper;
import com.everis.beca.domain.model.Pagamento;
import com.everis.beca.domain.service.PagamentoService;


/**
 * 
 * Essa parte é responsável pelo registro de pagamentos. O pagamento é registrado fornecendo o ID da 
 * entrada junto com o valor que foi pago pelo cliente (não necessariamente o valor gerado 
 * automaticamente pelo sistema). 
 * O valor total do estacionamento é gerado usando o preço de estacionamento do tipo do veículo 
 * estacionado multiplicado pela quantidade de horas (o termo "horas" foi usado para facilitar o 
 * entendimento. O sistema dá a liberdade de alterar o intevalo de tempo de cobrança do estacionamento) 
 * em que o veículo ficou estacionado. 
 * No momento que é registrado o pagamento da entrada, o sistema 
 * atualiza automaticamente o registro da entrada, preenchendo os campos ID do pagamento e a data e hora da saída.
 * 
 * @author uwerlich
 *
 */
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;

	private PagamentoModelMapper pagamentoModelMapper = new PagamentoModelMapper();

	
	/**
	 * Retorna o pagamento específicado pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> buscar(@PathVariable Long id) {
		Optional<Pagamento> pagamento = pagamentoService.buscar(id);
		if (!pagamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pagamento.get());
	}
	
	
	/**
	 * Retorna o valor total do estacionamento a ser pago pela entrada referenciada pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/consulta/{idEntrada}")
	public Pagamento consultarValor(@PathVariable Long idEntrada) {
		return pagamentoService.consultarValor(idEntrada);
	}

	
	/**
	 * Gera o registro do pagamento para a entrada referenciada pelo ID na URL. 
	 * Retorna o pagamento feito, e a URL de consulta do pagamento no header.
	 * @author Ulysses Werlich
	 **/
	@PostMapping("/{idEntrada}")
	public ResponseEntity<Pagamento> pagarValorEntrada(@PathVariable Long idEntrada, @Valid @RequestBody PagamentoInputDTO pagamentoDTO) {
		Pagamento pagamento = pagamentoModelMapper.converterParaModelo(pagamentoDTO);
		pagamento = pagamentoService.salvar(idEntrada, pagamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pagamento.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pagamento);
	}

}
