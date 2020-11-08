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

import com.everis.beca.domain.model.TipoVeiculo;
import com.everis.beca.domain.model.Veiculo;
import com.everis.beca.domain.repository.TipoVeiculoRepository;
import com.everis.beca.domain.repository.VeiculoRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VeiculoServiceTest {

	@InjectMocks
	VeiculoService veiculoService;
	
	@InjectMocks
	TipoVeiculoService tipoVeiculoService;
	
	@Mock
	TipoVeiculoRepository tipoVeiculoRepository;
	
	@Mock
	VeiculoRepository veiculoRepository;
	
	private Veiculo veiculo = new Veiculo();
	private Veiculo veiculo2 = new Veiculo();
	private TipoVeiculo tipoVeiculo = new TipoVeiculo();
	
	@BeforeEach
	public void setup() {
		
		tipoVeiculo.setId(2L);
		
		veiculo.setId(1L);
		veiculo.setPlaca("jfi2384");
		veiculo.setCor("Azul");
		veiculo.setTipoVeiculo(tipoVeiculo);
		
		veiculo2.setId(1L);
		veiculo2.setPlaca("jfi2384");
		veiculo2.setCor("Azul");
		veiculo2.setTipoVeiculo(tipoVeiculo);
	}
	
	@Test
	public void deveRetornarListaComDoisElementoQuandoListar() {
		
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculos.add(veiculo);
		veiculos.add(veiculo2);
		
		Mockito.when(veiculoRepository.findAll()).thenReturn(veiculos);
		
		List<Veiculo> veiculosTeste = veiculoService.listar();
		
		Assertions.assertEquals(veiculosTeste.get(0), veiculo);
		Assertions.assertEquals(veiculosTeste.get(1), veiculo2);
	}
	
	@Test
	public void deveBuscarUmVeiculoQuandoPesquisarPorId() {
		Mockito.when(veiculoRepository.findById(anyLong())).thenReturn(Optional.of(veiculo));
		
		Optional<Veiculo> teste = veiculoService.buscar(1L);
		
		Assertions.assertEquals(teste.get(), veiculo);
		
	}
	
	@Test
	public void deveRetornarUmVeiculoQuandoProcurarPorPlaca() {
		
		Mockito.when(veiculoRepository.findByPlaca(anyString())).thenReturn(Optional.of(veiculo));
		
		Optional<Veiculo> teste = veiculoService.buscarPorPlaca("jfi2384");
		
		Assertions.assertEquals(teste.get(), veiculo);
	}
	
}
