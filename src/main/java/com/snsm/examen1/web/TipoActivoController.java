package com.snsm.examen1.web;

import java.util.List;
import com.snsm.examen1.domain.TipoActivo;
import com.snsm.examen1.service.TipoActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TipoActivoController
 */
@RestController
@RequestMapping("/api/tipos-activo")
public class TipoActivoController {

    @Autowired
    private TipoActivoService tipoActivoService;


    @GetMapping("/{id}")
    public TipoActivo getDatoById(@PathVariable(value = "id") Long id) {
        try {
            return this.tipoActivoService.getTipoActivoById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public List<TipoActivo> getAllTipoActivo() {
        try {
            return this.tipoActivoService.getAllTipoActivo();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public TipoActivo updateTipoActivo(@PathVariable(value = "id") Long id,
                                       @RequestBody TipoActivo ta) {
        try {
            return this.tipoActivoService.update(id, ta);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping
    public TipoActivo createTipoActivo(@RequestBody TipoActivo ta) {
        try {
            return this.tipoActivoService.create(ta);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoActivo(@PathVariable(
            value = "id") Long id) {
        try {
            return this.tipoActivoService.delete(id);
        } catch (Exception e) {
            return null;
        }
    }

}
