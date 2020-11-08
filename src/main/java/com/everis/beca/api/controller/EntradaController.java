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


/**
 * 
 * Essa parte é responsável pelo registro e controle de entradas. A data e hora tanto de saída quanto 
 * entrada são gerados automaticamente pelo sistema. A data e hora de entrada é gerado quando é feito 
 * o cadastro da entrada. Os campos data e hora de saída e ID do pagamento são gerados quando é 
 * registrado o pagamento.
 * 
 * @author uwerlich
 *
 */
@RestController
@RequestMapping("/entradas")
public class EntradaController {

	@Autowired
	private EntradaService entradaService;
	
	private EntradaModelMapper entradaModelMapper = new EntradaModelMapper();
	
	
	/**
	 * Retorna as entradas registradas no sistema.
	 * @author Ulysses Werlich
	 **/
	@GetMapping
	public List<EntradaOutputDTO> listar(){
		return entradaModelMapper.converterListaParaDTO(entradaService.listar());
	}
	
	
	/**
	 * Retorna a entrada especificada pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/{id}")
	public ResponseEntity<EntradaOutputDTO> buscar(@PathVariable Long id){
		Optional<Entrada> entrada = entradaService.buscar(id);
		if (!entrada.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entradaModelMapper.converterParaDTO(entrada.get()));
	}
	
	
	/**
	 * Retorna as entradas abertas (sem o registro de saída e de pagamento) registradas no sistema.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/abertos")
	public List<EntradaOutputDTO> listarPorAbertos(){
		List<Entrada> entradas = entradaService.listarPorAbertos();
		return entradaModelMapper.converterListaParaDTO(entradas);
	}
	
	
	/**
	 * Retorna as entradas registradas no sistema que pertencem ao cliente específicado pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/cliente/{idCliente}")
	public List<EntradaOutputDTO> listarPorCliente(@PathVariable Long idCliente){
		List<Entrada> entradas = entradaService.listarPorCliente(idCliente);
		return entradaModelMapper.converterListaParaDTO(entradas);
	}
	
	
	/**
	 * Retorna as entradas registradas no sistema que pertencem ao veículo específicado pelo ID na URL.
	 * @author Ulysses Werlich
	 **/
	@GetMapping("/veiculo/{idVeiculo}")
	public List<EntradaOutputDTO> listarPorVeiculo(@PathVariable Long idVeiculo){
		List<Entrada> entradas = entradaService.listarPorCliente(idVeiculo);
		return entradaModelMapper.converterListaParaDTO(entradas);
	}
	
	
	/**
	 * Grava no banco de dados uma nova entrada, e retorna a entrada cadastrada junto com o ID, 
	 * e a URL de consulta no header da resposta.
	 * @author Ulysses Werlich
	 **/
	@PostMapping
	public ResponseEntity<EntradaOutputDTO> cadastrar(@Valid @RequestBody EntradaInputDTO entradaRepresent){
		Entrada entrada = entradaModelMapper.converterParaModelo(entradaRepresent);
		entrada = entradaService.salvar(entrada);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entrada.getId())
				.toUri();
		return ResponseEntity.created(uri).body(entradaModelMapper.converterParaDTO(entrada));
	}
	
	
	/**
	 * Altera no banco de dados a entrada especificado pelo ID na URL, e retorna a entrada alterada junto com o ID.
	 * @author Ulysses Werlich
	 **/
	@PutMapping("/{id}")
	public ResponseEntity<EntradaOutputDTO> alterar(@Valid @RequestBody EntradaInputDTO entradaRepresent, @PathVariable Long id){
		if (!entradaService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		Entrada entrada = entradaModelMapper.converterParaModelo(entradaRepresent);
		entrada.setId(id);
		return ResponseEntity.ok(entradaModelMapper.converterParaDTO(entrada));				
	}
	
	
	/**
	 * Exclui no banco de dados a entrada especificada pelo ID na URL, e retorna o status 204 No Content.
	 * @author Ulysses Werlich
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!entradaService.existe(id)) {
			return ResponseEntity.notFound().build();
		}
		entradaService.excluir(id);
		return ResponseEntity.noContent().build();				
	}
}