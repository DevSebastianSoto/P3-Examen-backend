package com.snsm.examen1.repository;

import com.snsm.examen1.domain.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TrabajadorRepository
 */
@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {


}
