package com.everis.beca.api.utils;

import com.everis.beca.api.model.PagamentoInputDTO;
import com.everis.beca.domain.model.Pagamento;

public class PagamentoModelMapper {
	
	public Pagamento converterParaModelo(PagamentoInputDTO pagamentoDTO) {
		Pagamento pagamento = new Pagamento();
		
		pagamento.setValorPagamento(pagamentoDTO.getValorPagamento());

		return pagamento;
	}
	
}
