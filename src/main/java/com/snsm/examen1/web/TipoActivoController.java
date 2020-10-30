package com.snsm.examen1.web;

import java.util.List;
import com.snsm.examen1.domain.TipoActivo;
import com.snsm.examen1.service.TipoActivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/tipos-activo")
public class TipoActivoController {

    @Autowired
    private TipoActivoService tipoActivoService;

    private static Logger logger =
            LoggerFactory.getLogger(TipoActivoController.class);

    @GetMapping("/{id}")
    public TipoActivo getDatoById(@PathVariable(value = "id") Long id) {
        try {
            return this.tipoActivoService.getTipoActivoById(id);
        } catch (Exception e) {
            logger.warn("Get failed: " + e.toString());
            return null;
        }
    }

    @GetMapping
    public List<TipoActivo> getAllTipoActivo() {
        try {
            logger.info("Get All");
            return this.tipoActivoService.getAllTipoActivo();
        } catch (Exception e) {
            logger.warn("Get failed: " + e.toString());
            return null;
        }
    }

    @PutMapping("/{id}")
    public TipoActivo updateTipoActivo(@PathVariable(value = "id") Long id,
                                       @RequestBody TipoActivo ta) {
        try {
            logger.info("Update: " + ta.toString());
            return this.tipoActivoService.update(id, ta);
        } catch (Exception e) {
            logger.warn("Put failed: " + e.toString());
            return null;
        }
    }

    @PostMapping
    public TipoActivo createTipoActivo(@RequestBody TipoActivo ta) {
        try {
            logger.info("Create: " + ta.toString());
            return this.tipoActivoService.create(ta);
        } catch (Exception e) {
            logger.warn("Post failed: " + e.toString());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoActivo(@PathVariable(
            value = "id") Long id) {
        try {
            logger.info("Delete: Tipo Activo #" + id);
            return this.tipoActivoService.delete(id);
        } catch (Exception e) {
            logger.warn("Delete failed: " + e.toString());
            return null;
        }
    }

}
