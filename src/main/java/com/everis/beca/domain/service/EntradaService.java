package com.everis.beca.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.beca.domain.exception.RegraDeNegocioException;
import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.model.Veiculo;
import com.everis.beca.domain.repository.EntradaRepository;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository entradaRepository;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private ClienteService clienteService;

	public List<Entrada> listar() {
		return entradaRepository.findAll();
	}

	public Optional<Entrada> buscar(Long id) {
		return entradaRepository.findById(id);
	}

	public List<Entrada> buscarPorCliente(Long idCliente) {
		return entradaRepository.findByCliente(new Cliente(idCliente));
	}
	
	public List<Entrada> buscarPorVeiculo(Long idVeiculo) {
		return entradaRepository.findByVeiculo(new Veiculo(idVeiculo));
	}

	public Entrada salvar(Entrada entrada) {
	
		Veiculo veiculo = veiculoService.buscar(entrada.getVeiculo().getId())
			.orElseThrow(() -> new RegraDeNegocioException("Veiculo não encontrado no sistema"));
		
		Cliente cliente = clienteService.buscar(entrada.getCliente().getId())
			.orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado no sistema"));
		
		entrada.setVeiculo(veiculo);
		entrada.setCliente(cliente);
		entrada.setDataHoraEntrada(OffsetDateTime.now());
		
		return entradaRepository.save(entrada);
	}

	public void excluir(Long id) {
		entradaRepository.deleteById(id);
	}

	public boolean existe(Long id) {
		return entradaRepository.existsById(id);
	}
	
//	public boolean existe(String placa) {
//		return entradaRepository.findByPlaca(placa).isPresent();
//	}
//	
}
