package com.gepatri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gepatri.dominio.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

	@Transactional(readOnly=true)
	public Page<Marca> findByNomeContaining(@Param("nome") String nome, Pageable  pageRequest);

	@Transactional(readOnly=true)
	public Marca findByNome(@Param("nome") String nome);
	
}
