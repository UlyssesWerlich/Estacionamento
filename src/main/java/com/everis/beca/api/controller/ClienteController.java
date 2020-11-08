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

import com.everis.beca.api.model.ClienteInputDTO;
import com.everis.beca.api.model.ClienteNomeInputDTO;
import com.everis.beca.api.utils.ClienteModelMapper;
import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.service.ClienteService;


/**
 * 
 * Essa parte é responsável pelo registro e controle dos clientes do estacionamento. 
 * Os campos Celular e E-mail são opcionais. O campo CPF deve ser único para cada cliente.
 * 
 * @author Ulysses Werlich
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	
	@Autowired
	private ClienteService clienteService;
	
	private ClienteModelMapper clienteModelMapper = new ClienteModelMapper();
	
	
	/**
	 * Retorna os clientes cadastrados no sistema.
	 * @author Ulysses Werlich
	 **/
	@GetMapping
	public List<Cliente> listar(){
		return clienteService.listar();
	}
	
	
	/**
	 * Retorna os clientes cujo o nome e/ou o sobrenome satisfaça a pesquisa feita no corpo da requisição.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/nome")
	public List<Cliente> buscarPorNome(@Valid @RequestBody ClienteNomeInputDTO clienteNome) {
		return clienteService.listarPorNome(clienteNome.getNome());
	}
	
	
	/**
	 * Retorna o cliente especificado pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id){
		Optional<Cliente> cliente = clienteService.buscar(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());		
	}
	
	
	/**
	 * Retorna o cliente com o CPF específicado na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
		Optional<Cliente> cliente = clienteService.buscarPorCpf(cpf);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
	
	
	/**
	 * Grava no banco de dados um novo cliente, e retorna o cliente cadastrado junto com o ID, 
	 * e a URL de consulta no header da resposta.
	 * @author Ulysses Werlich
	 **/
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody ClienteInputDTO clienteDTO) {
		Cliente cliente = clienteService.salvar(clienteModelMapper.converterParaModelo(clienteDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	
	/**
	 * Altera no banco de dados o cliente especificado pelo ID na URL, e retorna o cliente alterado junto com o ID.
	 * @author Ulysses Werlich
	 **/
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@Valid @RequestBody ClienteInputDTO clienteDTO, @PathVariable Long id) {
		if (!clienteService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = clienteModelMapper.converterParaModelo(clienteDTO);
		cliente.setId(id);
		return ResponseEntity.ok(clienteService.salvar(cliente)) ;
	}
	
	
	/**
	 * Exclui no banco de dados o cliente especificado pelo ID na URL, e retorna o status 204 No Content.
	 * @author Ulysses Werlich
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!clienteService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
