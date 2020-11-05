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

import com.everis.beca.api.model.ClienteNome;
import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id){
		Optional<Cliente> cliente = clienteService.buscar(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());		
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
		Optional<Cliente> cliente = clienteService.buscarPorCpf(cpf);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
	
	@GetMapping("/nome")
	public List<Cliente> buscarPorNome(@Valid @RequestBody ClienteNome cliente) {
		return clienteService.buscarPorNome(cliente.getNome());
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
		clienteService.salvar(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		if (!clienteService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		return ResponseEntity.ok(clienteService.alterar(cliente)) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!clienteService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
