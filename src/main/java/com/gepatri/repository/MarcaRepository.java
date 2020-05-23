package com.gepatri.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gepatri.dominio.Marca;
import com.gepatri.dominio.Patrimonio;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	public Page<Marca> findByNomeContaining(@Param("nome") String nome, Pageable  pageRequest);
}
