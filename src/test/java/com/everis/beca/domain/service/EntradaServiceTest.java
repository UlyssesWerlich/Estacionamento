package com.everis.beca.domain.service;

import static org.mockito.ArgumentMatchers.anyLong;

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

import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.model.TipoVeiculo;
import com.everis.beca.domain.model.Veiculo;
import com.everis.beca.domain.repository.EntradaRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EntradaServiceTest {

	@InjectMocks
	EntradaService entradaService;
	
	@Mock
	EntradaRepository entradaRepository;
	
	private TipoVeiculo tipoVeiculo = new TipoVeiculo();
	
	private Veiculo veiculo = new Veiculo();
	
	private Cliente cliente = new Cliente();
	
	private Entrada entrada = new Entrada();
	private Entrada entrada2 = new Entrada();
	
	@BeforeEach
	public void setup() {
		tipoVeiculo.setId(1L);
		cliente.setId(1L);
		veiculo.setId(1L);
		
		entrada.setId(1L);
		entrada.setCliente(cliente);
		entrada.setVeiculo(veiculo);
		
		entrada2.setId(2L);
		entrada2.setCliente(cliente);
		entrada2.setVeiculo(veiculo);
	}
	
	@Test
	public void deveRetornarListaComDoisElementoQuandoListar() {
		
		List<Entrada> entradas = new ArrayList<Entrada>();
		entradas.add(entrada);
		entradas.add(entrada2);
		
		Mockito.when(entradaRepository.findAll()).thenReturn(entradas);
		
		List<Entrada> entradasTeste = entradaService.listar();
		
		Assertions.assertEquals(entrada, entradasTeste.get(0));
		Assertions.assertEquals(entrada2, entradasTeste.get(1));
	}
	
	@Test
	public void deveBuscarUmEntradaQuandoPesquisarPorId() {
		Mockito.when(entradaRepository.findById(anyLong())).thenReturn(Optional.of(entrada));
		
		Optional<Entrada> teste = entradaService.buscar(1L);
		
		Assertions.assertEquals(entrada.getCliente(), teste.get().getCliente());
		
	}
	
	@Test
	public void deveRetornarListaDeEntradasAbertas() {
		
		List<Entrada> entradas = new ArrayList<Entrada>();
		entradas.add(entrada);
		
		Mockito.when(entradaRepository.findByPagamento(null)).thenReturn(entradas);
		
		List<Entrada> entradasTeste = entradaService.listarPorAbertos();
		
		Assertions.assertEquals(entradas.get(0), entradasTeste.get(0));
	}
}
