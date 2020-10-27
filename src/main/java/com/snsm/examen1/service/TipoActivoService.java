package com.snsm.examen1.service;

import java.util.List;
import javax.transaction.Transactional;
import com.snsm.examen1.domain.TipoActivo;
import com.snsm.examen1.exception.ResourceNotFoundException;
import com.snsm.examen1.repository.TipoActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * TipoActivoService
 */
@Service
@Transactional
public class TipoActivoService {

    @Autowired
    private TipoActivoRepository tipoActivoRepository;

    public TipoActivo getTipoActivoById(long id) {
        return this.tipoActivoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoActivo", "id", id));
    }

    public List<TipoActivo> getAllTipoActivo() {
        return this.tipoActivoRepository.findAll();
    }

    public TipoActivo create(TipoActivo ta) {
        return this.tipoActivoRepository.save(ta);
    }

    public TipoActivo update(Long id, TipoActivo nta) {
        TipoActivo ta = this.tipoActivoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoActivo", "id", id));

        ta.setNombre(nta.getNombre());
        ta.setDescripcion(nta.getDescripcion());
        ta.setEstado(nta.getEstado());
        ta.setActivosFisicos(nta.getActivosFisicos());
        return this.tipoActivoRepository.save(ta);
    }

    public ResponseEntity<Object> delete(Long id) {
        TipoActivo ta = this.tipoActivoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoActivo", "id", id));
        this.tipoActivoRepository.delete(ta);
        return ResponseEntity.ok().build();
    }
}
