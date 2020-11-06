package com.everis.beca.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.beca.domain.model.Cliente;
import com.everis.beca.domain.model.Entrada;
import com.everis.beca.domain.model.Pagamento;
import com.everis.beca.domain.model.Veiculo;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long>{
	List<Entrada> findByCliente(Cliente cliente);
	List<Entrada> findByVeiculo(Veiculo veiculo);
	List<Entrada> findByPagamento(Pagamento pagamento);
	
	
}
