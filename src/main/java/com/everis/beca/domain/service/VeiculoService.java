package com.everis.beca.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.beca.domain.exception.RegraDeNegocioException;
import com.everis.beca.domain.model.TipoVeiculo;
import com.everis.beca.domain.model.Veiculo;
import com.everis.beca.domain.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private TipoVeiculoService tipoVeiculoService;

	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}

	public Optional<Veiculo> buscar(Long id) {
		return veiculoRepository.findById(id);
	}

	public Optional<Veiculo> buscarPorPlaca(String placa) {
		return veiculoRepository.findByPlaca(placa);
	}

	public Veiculo salvar(Veiculo veiculo) {
		if (existe(veiculo.getPlaca()) && veiculo.getId() != buscarPorPlaca(veiculo.getPlaca()).get().getId()) {
			throw new RegraDeNegocioException("Placa já existe no sistema");
		}
		
		TipoVeiculo tipoVeiculo = tipoVeiculoService.buscar(veiculo.getTipoVeiculo().getId())
			.orElseThrow(() -> new RegraDeNegocioException("Tipo de veículo não encontrado no sistema"));
		
		veiculo.setTipoVeiculo(tipoVeiculo);
		
		return veiculoRepository.save(veiculo);
	}

	public void excluir(Long id) {
		veiculoRepository.deleteById(id);
	}

	public boolean existe(Long id) {
		return veiculoRepository.existsById(id);
	}
	
	public boolean existe(String placa) {
		return veiculoRepository.findByPlaca(placa).isPresent();
	}
}
