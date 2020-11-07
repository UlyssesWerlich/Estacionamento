package com.everis.beca.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.beca.domain.exception.RegraDeNegocioException;
import com.everis.beca.domain.model.TipoVeiculo;
import com.everis.beca.domain.repository.TipoVeiculoRepository;

@Service
public class TipoVeiculoService {
	
	@Autowired
	private TipoVeiculoRepository tipoVeiculoRepository;
	
	public List<TipoVeiculo> listar(){
		return tipoVeiculoRepository.findAll();
	}
	
	public Optional<TipoVeiculo> buscar(Long id) {
		return tipoVeiculoRepository.findById(id);
	}
	
	public Optional<TipoVeiculo> buscar(String nome) {
		return tipoVeiculoRepository.findByNome(nome);
	}
	
	public List<String> listarTiposDeVeiculos() {
		ArrayList<String> tipos = new ArrayList<String>();
		
		for ( TipoVeiculo tipoVeiculo : tipoVeiculoRepository.findAll()) {
			tipos.add(tipoVeiculo.getNome());
		}
		
		return tipos;
	}
	
	public TipoVeiculo salvar(TipoVeiculo tipoVeiculo) {
		if (existe(tipoVeiculo.getNome())) {
			throw new RegraDeNegocioException("Tipo de veículo já existe no sistema");
		}
		return tipoVeiculoRepository.save(tipoVeiculo);
	}

	public void excluir(Long id) {
		tipoVeiculoRepository.deleteById(id);
	}
	
	public boolean existe(String nome) {
		return tipoVeiculoRepository.findByNome(nome).isPresent();
	}
	
	public boolean existe(Long id) {
		return tipoVeiculoRepository.findById(id).isPresent();
	}
	
}