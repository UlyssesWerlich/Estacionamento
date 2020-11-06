package com.everis.beca.api.utils;

import com.everis.beca.api.model.ClienteInputDTO;
import com.everis.beca.domain.model.Cliente;

public class ClienteModelMapper {

	public Cliente converterParaModelo(ClienteInputDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.setCelular(clienteDTO.getCelular());
		cliente.setEmail(clienteDTO.getEmail());
		return cliente;
	}
	
}
