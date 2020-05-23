package com.gepatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gepatri.dominio.TomboNumero;

@Repository
public interface TomboNumberRepository extends JpaRepository<TomboNumero, Integer> {

}
