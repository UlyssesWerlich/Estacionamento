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

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;

	private PagamentoModelMapper pagamentoModelMapper = new PagamentoModelMapper();

	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> buscar(@PathVariable Long id) {
		Optional<Pagamento> pagamento = pagamentoService.buscar(id);
		if (!pagamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pagamento.get());
	}

	@GetMapping("/consulta/{idEntrada}")
	public Pagamento consultarValor(@PathVariable Long idEntrada) {
		return pagamentoService.consultarValor(idEntrada);
	}

	@PostMapping("/{idEntrada}")
	public ResponseEntity<Pagamento> pagarValorEntrada(@PathVariable Long idEntrada, @Valid @RequestBody PagamentoInputDTO pagamentoDTO) {
		Pagamento pagamento = pagamentoModelMapper.converterParaModelo(pagamentoDTO);
		pagamento = pagamentoService.salvar(idEntrada, pagamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pagamento.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pagamento);
	}

}
