package com.snsm.examen1.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.snsm.examen1.domain.Trabajador;
import com.snsm.examen1.exception.ResourceNotFoundException;
import com.snsm.examen1.repository.ActivoFisicoDeTrabajadorRepository;
import com.snsm.examen1.repository.TrabajadorRepository;
import com.snsm.examen1.web.TipoActivoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * TrabajadorService
 */
@Service
@Transactional
public class TrabajadorService {

    private static Logger logger =
            LoggerFactory.getLogger(TrabajadorService.class);

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private ActivoFisicoDeTrabajadorRepository aftRepository;

    public Trabajador getTrabajadorById(long id) {
        Trabajador trab = this.trabajadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Trabajador", "id", id));
        boolean hasActivo = aftRepository.findAll().stream()
                .anyMatch(item -> item.getTrabajador().getId() == trab.getId());
        logger.warn(hasActivo + "");
        trab.setHasActivo(hasActivo);
        return trab;
    }

    public List<Trabajador> getAllTrabajador() {
        var aftList = aftRepository.findAll();
        return this.trabajadorRepository.findAll().stream().map(trab -> {
            trab.setHasActivo(aftList.stream()
                    .anyMatch(e -> e.getTrabajador().getId() != trab.getId()));
            logger.warn("Activos asociados:\t" + aftList.size());
            logger.warn(trab.isHasActivo() + "");
            return trab;
        }).collect(Collectors.toList());
    }

    public Trabajador create(Trabajador trb) {
        return this.trabajadorRepository.save(trb);
    }

    public Trabajador update(Long id, Trabajador ntrb) {
        Trabajador trb = this.trabajadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Trabajador", "id", id));
        trb.setNombre(ntrb.getNombre());
        trb.setApellidos(ntrb.getApellidos());
        trb.setFechaNacimiento(ntrb.getFechaNacimiento());
        trb.setEstado(ntrb.getEstado());
        return this.trabajadorRepository.save(trb);
    }

    public ResponseEntity<Object> delete(Long id) {
        Trabajador trb = this.trabajadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Trabajador", "id", id));
        this.trabajadorRepository.delete(trb);
        return ResponseEntity.ok().build();
    }
}
