package com.snsm.examen1.repository;

import com.snsm.examen1.domain.TipoActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TipoActivoRepository
 */
@Repository
public interface TipoActivoRepository extends JpaRepository<TipoActivo, Long> {


}
