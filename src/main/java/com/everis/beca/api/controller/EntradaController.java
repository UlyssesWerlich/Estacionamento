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

import com.everis.beca.api.model.EntradaInputRepresentModel;
import com.everis.beca.api.utils.EntradaModelMapper;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.service.EntradaService;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

	@Autowired
	private EntradaService entradaService;
	
	private EntradaModelMapper entradaModelMapper = new EntradaModelMapper();
	
	@GetMapping
	public List<Entrada> listar(){
		return entradaService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entrada> buscar(@PathVariable Long id){
		Optional<Entrada> entradas = entradaService.buscar(id);
		if (!entradas.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entradas.get());
	}
	
	@GetMapping("/cliente/{idCliente}")
	public List<Entrada> buscarPorCliente(@PathVariable Long idCliente){
		List<Entrada> entradas = entradaService.buscarPorCliente(idCliente);
		return entradas;
	}
	
	@GetMapping("/veiculo/{idVeiculo}")
	public List<Entrada> buscarPorVeiculo(@PathVariable Long idVeiculo){
		List<Entrada> entradas = entradaService.buscarPorCliente(idVeiculo);
		return entradas;
	}
	
	@PostMapping
	public ResponseEntity<Entrada> cadastrar(@Valid @RequestBody EntradaInputRepresentModel entradaRepresent){
		Entrada entrada = entradaModelMapper.converterParaModelo(entradaRepresent);
		entrada = entradaService.salvar(entrada);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entrada.getId())
				.toUri();
		return ResponseEntity.created(uri).body(entrada);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Entrada> alterar(@Valid @RequestBody EntradaInputRepresentModel entradaRepresent, @PathVariable Long id){
		if (!entradaService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		Entrada entrada = entradaModelMapper.converterParaModelo(entradaRepresent);
		entrada.setId(id);
		return ResponseEntity.ok(entradaService.salvar(entrada));				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!entradaService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		entradaService.excluir(id);
		return ResponseEntity.noContent().build();				
	}
	
}
