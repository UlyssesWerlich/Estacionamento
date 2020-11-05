package com.everis.beca.domain.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.beca.domain.exception.RegraDeNegocioException;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.model.Pagamento;
import com.everis.beca.domain.repository.PagamentoRepository;

@Service
public class PagamentoService {

	private static Long intervaloPagamento = 60L;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private EntradaService entradaService;

	public List<Pagamento> listar() {
		return pagamentoRepository.findAll();
	}

	public Optional<Pagamento> buscar(Long id) {
		return pagamentoRepository.findById(id);
	}

	public Pagamento consultarValor(Long idEntrada) {
		Entrada entrada = consultarEntrada(idEntrada);
		entrada.setId(idEntrada);
		Pagamento pagamento = calculaValorDePagamento(entrada);
		return pagamento;
	}

//	public List<Pagamento> buscarPorCliente(Long idCliente) {
//		
//	}
//	
//	public List<Pagamento> buscarPorVeiculo(Long idVeiculo) {
//		
//	}
//	
	public Pagamento salvar(Long idEntrada, BigDecimal valorPagamento) {
		Entrada entrada = consultarEntrada(idEntrada);
		entrada.setId(idEntrada);
		entrada.setDataHoraSaida(OffsetDateTime.now());
		Pagamento pagamento = calculaValorDePagamento(entrada);
		pagamento.setValorPagamento(valorPagamento);
		
		
		entradaService.salvar(entrada);
		pagamentoRepository.save(pagamento);
		
		return pagamento;
	}

	public void excluir(Long id) {
		pagamentoRepository.deleteById(id);
	}

	public boolean existe(Long id) {
		return pagamentoRepository.existsById(id);
	}

	public Entrada consultarEntrada(Long idEntrada) {
		Entrada entrada = entradaService.buscar(idEntrada)
				.orElseThrow(() -> new RegraDeNegocioException("Entrada não encontrada no sistema"));
		if (entrada.getDataHoraSaida() != null) {
			throw new RegraDeNegocioException("Já foi feito o pagamento para a entrada " + idEntrada);
		}
		return entrada;
	}

	public Pagamento calculaValorDePagamento(Entrada entrada) {
		Pagamento pagamento = new Pagamento();
		Long momentoEntradaEmSegundos = entrada.getDataHoraEntrada().toEpochSecond();
		Long momentoPagamentoEmSegundos = OffsetDateTime.now().toEpochSecond();
		pagamento.setEntradaId(entrada.getId());
		pagamento.setTempoEstacionamento((momentoPagamentoEmSegundos - momentoEntradaEmSegundos) / 60);
		pagamento.setQuantidadeIntervaloDeTempo(pagamento.getTempoEstacionamento() / intervaloPagamento);

		pagamento.setValorTotalEntrada(BigDecimal.valueOf(pagamento.getQuantidadeIntervaloDeTempo()
				* entrada.getVeiculo().getTipoVeiculo().getPreco().doubleValue()));

		return pagamento;
	}
}
