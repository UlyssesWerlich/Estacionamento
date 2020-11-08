package com.everis.beca.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private Cliente cliente2 = new Cliente();
	
	@BeforeEach
	public void setup() {
		cliente.setId(1L);
		cliente.setNome("Ulysses Werlich Borges");
		cliente.setCpf("08926004970");
		
		cliente2.setId(1L);
		cliente2.setNome("Priscila Schlemper");
		cliente2.setCpf("08926004930");
	}
	
	@Test
	public void deveRetornarListaComDoisElementoQuandoListar() {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		clientes.add(cliente2);
		
		Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
		
		List<Cliente> clientesTeste = clienteService.listar();
		
		Assertions.assertEquals("Ulysses Werlich Borges", clientesTeste.get(0).getNome());
		Assertions.assertEquals("Priscila Schlemper", clientesTeste.get(1).getNome());
	}
	
	@Test
	public void deveBuscarUmClienteQuandoPesquisarPorId() {
		Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		
		Optional<Cliente> teste = clienteService.buscar(1L);
		
		Assertions.assertEquals(cliente.getNome(), teste.get().getNome());
		
	}
	
	@Test
	public void deveRetornarListaComUmElementoQuandoProcurarPorNome() {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		
		Mockito.when(clienteRepository.findByNomeContaining(anyString())).thenReturn(clientes);
		
		List<Cliente> clientesTeste = clienteService.listarPorNome("Ulysses");
		
		Assertions.assertEquals("Ulysses Werlich Borges", clientesTeste.get(0).getNome());
	}
	
	@Test
	public void deveSalvarUmCliente() {
		Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
		Cliente teste = clienteService.salvar(cliente);
		
		Assertions.assertEquals( teste, cliente );
	}
}
