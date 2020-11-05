package com.everis.beca.api.utils;

import com.everis.beca.api.model.VeiculoInputRepresentModel;
import com.everis.beca.domain.model.TipoVeiculo;
import com.everis.beca.domain.model.Veiculo;

public class VeiculoModelMapper {
	
	public Veiculo converterParaModelo(VeiculoInputRepresentModel veiculoRepresent) {
		Veiculo veiculo = new Veiculo();
		veiculo.setModelo(veiculoRepresent.getModelo());
		veiculo.setCor(veiculoRepresent.getCor());
		veiculo.setPlaca(veiculoRepresent.getPlaca());
		TipoVeiculo tipo = new TipoVeiculo();
		tipo.setId(veiculoRepresent.getTipoVeiculoId());
		veiculo.setTipoVeiculo(tipo);
		return veiculo;
	}
	
}
