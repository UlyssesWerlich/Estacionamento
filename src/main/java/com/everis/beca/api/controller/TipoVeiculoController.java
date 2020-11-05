package com.everis.beca.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.everis.beca.domain.model.TipoVeiculo;
import com.everis.beca.domain.service.TipoVeiculoService;

@RestController
@RequestMapping("/tipos-veiculos")
public class TipoVeiculoController {

	@Autowired
	private TipoVeiculoService tipoVeiculoService;

	@GetMapping
	public List<TipoVeiculo> listar() {
		return tipoVeiculoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoVeiculo> buscar(@PathVariable Long id) {
		Optional<TipoVeiculo> tabelaPreco = tipoVeiculoService.buscar(id);
		if (!tabelaPreco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tabelaPreco.get());
	}

	@GetMapping("/nomes/{nome}")
	public ResponseEntity<TipoVeiculo> buscarNomes(@PathVariable String nome) {
		Optional<TipoVeiculo> tabelaPreco = tipoVeiculoService.buscar(nome);
		if (!tabelaPreco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tabelaPreco.get());
	}

	@GetMapping("/nomes")
	public List<String> listarTiposDeVeiculos(){
		return tipoVeiculoService.listarTiposDeVeiculos();
	}

	@PostMapping
	public ResponseEntity<TipoVeiculo> cadastrar(@Valid @RequestBody TipoVeiculo tipoVeiculo){
		tipoVeiculoService.salvar(tipoVeiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoVeiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(tipoVeiculo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoVeiculo> alterar(@Valid @RequestBody TipoVeiculo tipoVeiculo, @PathVariable Long id) {
		if (!tipoVeiculoService.existe(id)){
			return ResponseEntity.notFound().build();
		}
		tipoVeiculo.setId(id);
		return ResponseEntity.ok(tipoVeiculoService.alterar(tipoVeiculo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if (!tipoVeiculoService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		tipoVeiculoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
