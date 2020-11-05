package com.everis.beca.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.beca.domain.model.TipoVeiculo;

@Repository
public interface TipoVeiculoRepository extends JpaRepository<TipoVeiculo, Long>{
	Optional<TipoVeiculo> findByNome(String nome);
}
