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

import com.everis.beca.api.model.VeiculoInputDTO;
import com.everis.beca.api.utils.VeiculoModelMapper;
import com.everis.beca.domain.model.Veiculo;
import com.everis.beca.domain.service.VeiculoService;


/**
 * 
 * Essa parte é responsável pelo registro e controle dos veículos. O campo modelo do veículo é opcional. 
 * O campo placa deve ser único para cada veículo cadastrado.
 * 
 * @author Ulysses Werlich
 *
 */
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	private VeiculoModelMapper veiculoModelMapper = new VeiculoModelMapper();
	
	
	/**
	 * Retorna os veiculos cadastrados no sistema.
	 * @author Ulysses Werlich
	 **/
	@GetMapping
	public List<Veiculo> listar(){
		return veiculoService.listar();
	}
	
	
	/**
	 * Retorna o veículo especificado pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscar(@PathVariable Long id){
		Optional<Veiculo> veiculos = veiculoService.buscar(id);
		if (!veiculos.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculos.get());
	}
	
	
	/**
	 * Retorna o veículo com a placa específicada na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/placa/{placa}")
	public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa){
		Optional<Veiculo> veiculos = veiculoService.buscarPorPlaca(placa);
		if (!veiculos.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculos.get());
	}
	
	
	/**
	 * Grava no banco de dados um novo veículo, e retorna o veículo cadastrado junto com o ID, 
	 * e a URL de consulta no header da resposta.
	 * @author Ulysses Werlich
	 **/
	@PostMapping
	public ResponseEntity<Veiculo> cadastrar(@Valid @RequestBody VeiculoInputDTO veiculoRepresent){
		Veiculo veiculo = veiculoModelMapper.converterParaModelo(veiculoRepresent);
		veiculo = veiculoService.salvar(veiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(veiculo);
	}
	
	
	/**
	 * Altera no banco de dados o veículo especificado pelo ID na URL, e retorna o veículo alterado junto com o ID.
	 * @author Ulysses Werlich
	 **/
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> alterar(@Valid @RequestBody VeiculoInputDTO veiculoRepresent, @PathVariable Long id){
		if (!veiculoService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Veiculo veiculo = veiculoModelMapper.converterParaModelo(veiculoRepresent);
		veiculo.setId(id);
		return ResponseEntity.ok(veiculoService.salvar(veiculo));				
	}
	
	
	/**
	 * Exclui no banco de dados o veículo especificado pelo ID na URL, e retorna o status 204 No Content.
	 * @author Ulysses Werlich
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!veiculoService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculoService.excluir(id);
		return ResponseEntity.noContent().build();				
	}
}
