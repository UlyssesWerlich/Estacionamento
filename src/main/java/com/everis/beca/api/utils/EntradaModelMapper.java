package com.everis.beca.api.utils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.everis.beca.api.model.EntradaInputDTO;
import com.everis.beca.api.model.EntradaOutputDTO;
import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.model.Veiculo;

public class EntradaModelMapper {

	public Entrada converterParaModelo(@Valid EntradaInputDTO entradaRepresent) {
		Entrada entrada = new Entrada();
		entrada.setCliente(new Cliente(entradaRepresent.getClienteId()));
		entrada.setVeiculo(new Veiculo(entradaRepresent.getVeiculoId()));
		
		return entrada;
	}
	
	public EntradaOutputDTO converterParaDTO(Entrada entrada) {
		EntradaOutputDTO dto = new EntradaOutputDTO();
		dto.setId(entrada.getId());
		dto.setPlaca(entrada.getVeiculo().getPlaca());
		dto.setCor(entrada.getVeiculo().getCor());
		dto.setModelo(entrada.getVeiculo().getModelo());
		dto.setTipoVeiculo(entrada.getVeiculo().getTipoVeiculo().getNome());
		dto.setPrecoPorTipoVeiculo(entrada.getVeiculo().getTipoVeiculo().getPreco());;
		dto.setCpf(entrada.getCliente().getCpf());
		dto.setNome(entrada.getCliente().getNome());
		dto.setTelefone(entrada.getCliente().getTelefone());
		dto.setCelular(entrada.getCliente().getCelular());
		dto.setEmail(entrada.getCliente().getEmail());
		dto.setDataHoraEntrada(entrada.getDataHoraEntrada());
		dto.setDataHoraSaida(entrada.getDataHoraSaida());
		if (entrada.getPagamento() != null) {
			dto.setValorPagamento(entrada.getPagamento().getValorPagamento());
			dto.setValorTotalEntrada(entrada.getPagamento().getValorTotalEntrada());
			dto.setTempoEstacionamento(entrada.getPagamento().getTempoEstacionamento());
		}
		return dto;
	}
	
	public List<EntradaOutputDTO> converterListaParaDTO(List<Entrada> listaEntrada){
		List<EntradaOutputDTO> listaDTO = new ArrayList<EntradaOutputDTO>();
		for (Entrada entrada : listaEntrada) {
			listaDTO.add(converterParaDTO(entrada));
		}
		return listaDTO;
	}
}
