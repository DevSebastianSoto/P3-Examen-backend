package com.snsm.examen1.repository;

import com.snsm.examen1.domain.ActivoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ActivoFisicoRepository
 */
@Repository
public interface ActivoFisicoRepository
        extends JpaRepository<ActivoFisico, Long> {


}
