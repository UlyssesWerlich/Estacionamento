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


/**
 * 
 * Essa parte é responsável pelo registro e controle dos tipos de veículos permitidos no sistema, 
 * junto com o preço do estacionamento para cada tipo de veículo. 
 * É por meio do preço associado com o tipo de veículo que mais pra frente 
 * é calculado o valor total do estacionamento.
 * 
 * @author Ulysses Werlich
 *
 */
@RestController
@RequestMapping("/tipos-veiculos")
public class TipoVeiculoController {

	@Autowired
	private TipoVeiculoService tipoVeiculoService;
	
	
	/**
	 * Retorna os tipos de veículos cadastrados.
	 * @author Ulysses Werlich
	 **/
	@GetMapping
	public List<TipoVeiculo> listar() {
		return tipoVeiculoService.listar();
	}
	
	
	/**
	 * Retorna o tipo de veículo especificado pelo ID
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/{id}")
	public ResponseEntity<TipoVeiculo> buscar(@PathVariable Long id) {
		Optional<TipoVeiculo> tabelaPreco = tipoVeiculoService.buscar(id);
		if (!tabelaPreco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tabelaPreco.get());
	}

	
	/**
	 * Retorna o tipo de veículo com o nome específico.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/nomes/{nome}")
	public ResponseEntity<TipoVeiculo> buscarNomes(@PathVariable String nome) {
		Optional<TipoVeiculo> tabelaPreco = tipoVeiculoService.buscar(nome);
		if (!tabelaPreco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tabelaPreco.get());
	}

	
	/**
	 * Retorna uma lista de nomes dos tipos de veículos.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/nomes")
	public List<String> listarTiposDeVeiculos(){
		return tipoVeiculoService.listarTiposDeVeiculos();
	}
	
	
	/**
	 * Grava no banco de dados o novo tipo de veículo, e retorna o tipo de veículo cadastrado junto com o ID, 
	 * e a URL de consulta no header da resposta,
	 * @author Ulysses Werlich
	 **/
	@PostMapping
	public ResponseEntity<TipoVeiculo> cadastrar(@Valid @RequestBody TipoVeiculo tipoVeiculo){
		tipoVeiculoService.salvar(tipoVeiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoVeiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(tipoVeiculo);
	}
	
	
	/**
	 * Altera no banco de dados o tipo de veículo especificado pelo ID na URL, 
	 * e retorna o tipo de veículo alterado junto com o ID.
	 * @author Ulysses Werlich
	 **/
	@PutMapping("/{id}")
	public ResponseEntity<TipoVeiculo> alterar(@Valid @RequestBody TipoVeiculo tipoVeiculo, @PathVariable Long id) {
		if (!tipoVeiculoService.existe(id)){
			return ResponseEntity.notFound().build();
		}
		tipoVeiculo.setId(id);
		return ResponseEntity.ok(tipoVeiculoService.salvar(tipoVeiculo));
	}
	
	
	/**
	 * Exclui no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o status 204 No Content.
	 * @author Ulysses Werlich
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if (!tipoVeiculoService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		tipoVeiculoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
