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

import com.everis.beca.api.model.VeiculoInputRepresentModel;
import com.everis.beca.api.utils.VeiculoModelMapper;
import com.everis.beca.domain.model.Veiculo;
import com.everis.beca.domain.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	private VeiculoModelMapper veiculoModelMapper = new VeiculoModelMapper();
	
	@GetMapping
	public List<Veiculo> listar(){
		return veiculoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscar(@PathVariable Long id){
		Optional<Veiculo> veiculos = veiculoService.buscar(id);
		if (!veiculos.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculos.get());
	}
	
	@GetMapping("/placa/{placa}")
	public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa){
		Optional<Veiculo> veiculos = veiculoService.buscarPorPlaca(placa);
		if (!veiculos.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculos.get());
	}
	
	@PostMapping
	public ResponseEntity<Veiculo> cadastrar(@Valid @RequestBody VeiculoInputRepresentModel veiculoRepresent){
		Veiculo veiculo = veiculoModelMapper.converterParaModelo(veiculoRepresent);
		veiculo = veiculoService.salvar(veiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(veiculo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> alterar(@Valid @RequestBody VeiculoInputRepresentModel veiculoRepresent, @PathVariable Long id){
		if (!veiculoService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Veiculo veiculo = veiculoModelMapper.converterParaModelo(veiculoRepresent);
		veiculo.setId(id);
		return ResponseEntity.ok(veiculoService.salvar(veiculo));				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!veiculoService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculoService.excluir(id);
		return ResponseEntity.noContent().build();				
	}
}
