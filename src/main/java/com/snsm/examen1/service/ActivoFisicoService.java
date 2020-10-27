package com.snsm.examen1.service;

import java.util.List;
import javax.transaction.Transactional;
import com.snsm.examen1.domain.ActivoFisico;
import com.snsm.examen1.exception.ResourceNotFoundException;
import com.snsm.examen1.repository.ActivoFisicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * ActivoFisicoService
 */
@Service
@Transactional
public class ActivoFisicoService {

    private static Logger logger = LoggerFactory.getLogger(ActivoFisico.class);

    @Autowired
    private ActivoFisicoRepository activoFisicoRepository;

    public ActivoFisico getActivoFisicoById(long id) {
        return this.activoFisicoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ActivoFisico", "id", id));
    }

    public List<ActivoFisico> getAllActivoFisico() {
        return this.activoFisicoRepository.findAll();
    }

    public ActivoFisico create(ActivoFisico ta) {
        return this.activoFisicoRepository.save(ta);
    }

    public ActivoFisico update(Long id, ActivoFisico nta) {
        ActivoFisico ta = this.activoFisicoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ActivoFisico", "id", id));
        logger.warn(ta.toString());
        ta.setNombre(nta.getNombre());
        ta.setDescripcion(nta.getDescripcion());
        ta.setFechaIngreso(nta.getFechaIngreso());
        ta.setCantidad(nta.getCantidad());
        ta.setCantidadAsignados(nta.getCantidadAsignados());
        ta.setEstado(nta.getEstado());
        return this.activoFisicoRepository.save(ta);
    }

    public ResponseEntity<Object> delete(Long id) {
        ActivoFisico ta = this.activoFisicoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ActivoFisico", "id", id));
        this.activoFisicoRepository.delete(ta);
        return ResponseEntity.ok().build();
    }
}
