package com.snsm.examen1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.snsm.examen1.domain.ActivoFisico;
import com.snsm.examen1.domain.ActivoFisicoDeTrabajador;
import com.snsm.examen1.domain.Trabajador;
import com.snsm.examen1.exception.ResourceNotFoundException;
import com.snsm.examen1.repository.ActivoFisicoDeTrabajadorRepository;
import com.snsm.examen1.repository.ActivoFisicoRepository;
import com.snsm.examen1.repository.TrabajadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * ActivoFisicoDeTrabajadorService
 */
@Service
@Transactional
public class ActivoFisicoDeTrabajadorService {

    private static Logger logger =
            LoggerFactory.getLogger(ActivoFisicoDeTrabajador.class);

    @Autowired
    private ActivoFisicoDeTrabajadorRepository activoFisicoDeTrabajadorRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private ActivoFisicoRepository activoFisicoRepository;

    public ActivoFisicoDeTrabajador getActivoFisicoDeTrabajadorById(long id) {
        return this.activoFisicoDeTrabajadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ActivoFisicoDeTrabajador",
                        "id", id));
    }

    public List<ActivoFisicoDeTrabajador> getAllActivoFisicoDeTrabajador() {
        return this.activoFisicoDeTrabajadorRepository.findAll();
    }

    public ActivoFisicoDeTrabajador create(long idTrabajador,
                                           long idActivoFisico,
                                           LocalDate fechaAsignacion) {
        try {
            Trabajador trb =
                    trabajadorRepository.findById(idTrabajador).orElse(null);
            List<ActivoFisico> acfLst = activoFisicoRepository.findAll();
            ActivoFisico acf = acfLst.stream()
                    .filter(a -> a.getId() == idActivoFisico
                            && a.getEstado() != "INACTIVO")
                    .collect(Collectors.toList()).get(0);
            logger.debug("ActivoFisico esta activo");

            // Si existen los elementos
            if (trb != null && acf != null) {
                logger.debug("Trabajador y ActivoFisico existen");
                // Si el estado del activo es ACTIVO
                ActivoFisicoDeTrabajador act = new ActivoFisicoDeTrabajador();
                act.setEstado("ACTIVO");
                acf.setEstado("INACTIVO");
                act.setTrabajador(trb);
                act.setActivoFisico(acf);
                act.setFechaAsignacion(fechaAsignacion);
                return this.activoFisicoDeTrabajadorRepository.save(act);
            }
            return null;
        } catch (Exception e) {
            logger.error("Create Error: " + e.toString());
            return null;

        }
    }

    public ActivoFisicoDeTrabajador update(Long id,
                                           ActivoFisicoDeTrabajador nta) {
        try {
            ActivoFisicoDeTrabajador ta =
                    this.activoFisicoDeTrabajadorRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "ActivoFisicoDeTrabajador", "id", id));
            logger.warn(ta.toString());
            return activoFisicoDeTrabajadorRepository.save(ta);
        } catch (Exception e) {
            logger.warn(e.toString());
            return null;
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        ActivoFisicoDeTrabajador aft = this.activoFisicoDeTrabajadorRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException(
                        "ActivoFisicoDeTrabajador", "id", id));
        this.activoFisicoDeTrabajadorRepository.delete(aft);
        return ResponseEntity.ok().build();
    }

    public ActivoFisicoDeTrabajador close(long id) {
        try {
            ActivoFisicoDeTrabajador aft =
                    this.activoFisicoDeTrabajadorRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "ActivoFisicoDeTrabajador", "id", id));
            logger.warn(aft.toString());
            aft.setEstado("INACTIVO");
            aft.getActivoFisico().setEstado("ACTIVO");
            activoFisicoRepository.save(aft.getActivoFisico());
            return this.activoFisicoDeTrabajadorRepository.save(aft);
        } catch (Exception e) {
            logger.warn(e.toString());
            return null;
        }
    }
}
