package com.everis.beca.api.utils;

import javax.validation.Valid;

import com.everis.beca.api.model.EntradaInputRepresentModel;
import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.model.Veiculo;

public class EntradaModelMapper {

	public Entrada converterParaModelo(@Valid EntradaInputRepresentModel entradaRepresent) {
		Entrada entrada = new Entrada();
		entrada.setCliente(new Cliente(entradaRepresent.getClienteId()));
		entrada.setVeiculo(new Veiculo(entradaRepresent.getVeiculoId()));
		
		return entrada;
	}
}
