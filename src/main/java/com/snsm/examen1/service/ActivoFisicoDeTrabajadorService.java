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
    private TrabajadorService trabajadorService;

    @Autowired
    private ActivoFisicoRepository activoFisicoRepository;

    public ActivoFisicoDeTrabajador getActivoFisicoDeTrabajadorById(long id) {
        return this.activoFisicoDeTrabajadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ActivoFisicoDeTrabajador",
                        "id", id));
    }

    public List<ActivoFisicoDeTrabajador> getAllActivoFisicoDeTrabajador() {
        return this.activoFisicoDeTrabajadorRepository.findAll().stream()
                .map(aft -> {
                    if (aft.getEstado().equals("ACTIVO")) {
                        aft.getTrabajador().setHasActivo(true);
                    }
                    return aft;
                }).collect(Collectors.toList());
    }

    /**
     * Si existen trabajador y activo, y hay activos disponibles, se crea la
     * asignacion | Si el trabajador tiene un activo actualmente o no hay
     * activos, se retorna null
     */
    public ActivoFisicoDeTrabajador create(long idTrabajador,
                                           long idActivoFisico,
                                           LocalDate fechaAsignacion,
                                           String estado) {
        try {
            // cargar listas
            Trabajador trb = trabajadorService.getTrabajadorById(idTrabajador);
            List<ActivoFisico> acfLst = activoFisicoRepository.findAll();

            // obtener activo fisico
            ActivoFisico acf = activoFisicoRepository.findById(idActivoFisico)
                    .orElse(null);

            // Si existen los elementos
            if (trb != null && acf != null) {
                if (trb.isHasActivo()) {
                    logger.warn("El trabajador ya tiene un activo registrado");
                    return null;
                }
                if (acf.getCantidadAsignados() == acf.getCantidad()) {
                    acf.setEstado("INACTIVO");
                    this.activoFisicoRepository.save(acf);
                    logger.warn("No hay activos disponibles");
                    return null;
                }
                logger.warn("Se ha realizado el registro");
                acf.setCantidadAsignados(acf.getCantidadAsignados() + 1);
                trb.setHasActivo(true);
                ActivoFisicoDeTrabajador act = new ActivoFisicoDeTrabajador();
                act.setTrabajador(trb);
                act.setActivoFisico(acf);
                act.setFechaAsignacion(fechaAsignacion);
                act.setEstado(estado);
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

    /**
     * Si el activo de trabajador ya fue cerrado antes = null Al cerrar el
     * activo de trabajador, se cambia el estado a inactivo y el activo fisico
     * reduce en 1 la cantidad de asignados, debido a esto el activo fisico
     * siempre estara ACTIVO cuando se cierre una de sus relaciones.
     */
    public ActivoFisicoDeTrabajador close(long id) {
        try {
            ActivoFisicoDeTrabajador aft =
                    this.getActivoFisicoDeTrabajadorById(id);

            // Si el activo fisico esta inactivo, no hacer nada
            if (aft.getEstado().equals("INACTIVO")) {
                logger.warn(
                        "El activo de trabajador ya fue cerrado previamente");
                return null;
            }

            aft.setEstado("INACTIVO");
            aft.getTrabajador().setHasActivo(false);
            aft.getActivoFisico().setCantidadAsignados(
                    aft.getActivoFisico().getCantidadAsignados() - 1);
            aft.getActivoFisico().setEstado("ACTIVO");
            activoFisicoRepository.save(aft.getActivoFisico());
            return this.activoFisicoDeTrabajadorRepository.save(aft);
        } catch (Exception e) {
            logger.warn(e.toString());
            return null;
        }
    }
}
