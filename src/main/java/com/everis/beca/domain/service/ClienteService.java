package com.everis.beca.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.beca.domain.exception.RegraDeNegocioException;
import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	public List<Cliente> listarPorNome(String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}
	
	public Optional<Cliente> buscar(Long id) {
		return clienteRepository.findById(id);
	}
	
	public Optional<Cliente> buscarPorCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	public Cliente salvar(Cliente cliente) {
		if (existe(cliente.getCpf())) {
			throw new RegraDeNegocioException("CPF já existe no sistema");
		}
		return clienteRepository.save(cliente);
	}

	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public boolean existe(String cpf) {
		return clienteRepository.findByCpf(cpf).isPresent();
	}
	
	public boolean existe(Long id) {
		return clienteRepository.findById(id).isPresent();
	}
}
