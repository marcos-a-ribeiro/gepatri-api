package com.gepatri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gepatri.dominio.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {
	@Transactional(readOnly=true)
	public Page<Patrimonio> findByNomeContaining(@Param("nome") String nome, Pageable  pageRequest);
}
