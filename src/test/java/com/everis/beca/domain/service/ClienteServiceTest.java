package com.everis.beca.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.mockito.ArgumentMatchers.*;

import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClienteServiceTest {

	@InjectMocks
	ClienteService clienteService;
	
	@Mock
	ClienteRepository clienteRepository;
	
	private Cliente cliente = new Cliente();
	
	@BeforeEach
	public void setup() {
		cliente.setId(1L);
		cliente.setNome("Ulysses Werlich Borges");
		cliente.setCpf("08926004970");
	}
	
	@Test
	public void deveRetornarListaComUmElementoQuandoBuscarPeloNome() {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		Mockito.when(clienteRepository.findByNomeContaining(anyString())).thenReturn(clientes);
		
		List<Cliente> clientesTeste = clienteService.listarPorNome("jfwoijfew");
		Assertions.assertEquals("Ulysses Werlich Borges", clientesTeste.get(0).getNome());
	}
	
//	@Test
//	public void deveRetornarListaComClientes() {
//		String nome = "Werlich";
//		Cliente cliente = clienteService.listarPorNome(nome).get(0);
//		Cliente cliente2 = clienteService.listarPorNome(nome).get(1);
//		
//		Assertions.assertEquals("Ulysses Werlich Borges", cliente.getNome());
//		Assertions.assertEquals("Priscila Schlemper Werlich", cliente2.getNome());
//	}

	
}
