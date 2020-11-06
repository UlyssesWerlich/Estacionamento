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

import com.everis.beca.api.model.EntradaInputDTO;
import com.everis.beca.api.model.EntradaOutputDTO;
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
	public List<EntradaOutputDTO> listar(){
		return entradaModelMapper.converterListaParaDTO(entradaService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntradaOutputDTO> buscar(@PathVariable Long id){
		Optional<Entrada> entrada = entradaService.buscar(id);
		if (!entrada.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entradaModelMapper.converterParaDTO(entrada.get()));
	}
	
	@GetMapping("/abertos")
	public List<EntradaOutputDTO> listarPorAbertos(){
		List<Entrada> entradas = entradaService.listarPorAbertos();
		return entradaModelMapper.converterListaParaDTO(entradas);
	}
	
	@GetMapping("/cliente/{idCliente}")
	public List<EntradaOutputDTO> listarPorCliente(@PathVariable Long idCliente){
		List<Entrada> entradas = entradaService.listarPorCliente(idCliente);
		return entradaModelMapper.converterListaParaDTO(entradas);
	}
	
	@GetMapping("/veiculo/{idVeiculo}")
	public List<EntradaOutputDTO> listarPorVeiculo(@PathVariable Long idVeiculo){
		List<Entrada> entradas = entradaService.listarPorCliente(idVeiculo);
		return entradaModelMapper.converterListaParaDTO(entradas);
	}
	
	@PostMapping
	public ResponseEntity<EntradaOutputDTO> cadastrar(@Valid @RequestBody EntradaInputDTO entradaRepresent){
		Entrada entrada = entradaModelMapper.converterParaModelo(entradaRepresent);
		entrada = entradaService.salvar(entrada);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entrada.getId())
				.toUri();
		return ResponseEntity.created(uri).body(entradaModelMapper.converterParaDTO(entrada));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EntradaOutputDTO> alterar(@Valid @RequestBody EntradaInputDTO entradaRepresent, @PathVariable Long id){
		if (!entradaService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		Entrada entrada = entradaModelMapper.converterParaModelo(entradaRepresent);
		entrada.setId(id);
		return ResponseEntity.ok(entradaModelMapper.converterParaDTO(entrada));				
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
