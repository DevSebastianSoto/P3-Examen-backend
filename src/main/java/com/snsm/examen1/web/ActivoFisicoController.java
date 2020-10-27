package com.snsm.examen1.web;

import java.util.List;
import com.snsm.examen1.domain.ActivoFisico;
import com.snsm.examen1.domain.TipoActivo;
import com.snsm.examen1.service.ActivoFisicoService;
import com.snsm.examen1.service.TipoActivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * ActivoFisicoController
 */
@RestController
@RequestMapping("/api/activos-fisicos")
public class ActivoFisicoController {

    private static Logger logger =
            LoggerFactory.getLogger(ActivoFisicoController.class);

    @Autowired
    private ActivoFisicoService activoFisicoService;

    @Autowired
    private TipoActivoService tipoActivoService;


    @GetMapping("/{id}")
    public ActivoFisico getDatoById(@PathVariable(value = "id") Long id) {
        try {
            return this.activoFisicoService.getActivoFisicoById(id);
        } catch (Exception e) {
            logger.error("Get By Id failed: " + e.toString());
            return null;
        }
    }

    @GetMapping
    public List<ActivoFisico> getAllActivoFisico() {
        try {
            return this.activoFisicoService.getAllActivoFisico();
        } catch (Exception e) {
            logger.error("Get All failed: " + e.toString());
            return null;
        }
    }

    @PutMapping("/{id}")
    public ActivoFisico updateActivoFisico(@PathVariable(value = "id") Long id,
                                           @RequestBody ActivoFisico af) {
        try {
            this.activoFisicoService.update(id, af);
            return af;
        } catch (Exception e) {
            logger.error("Update failed: " + e.toString());
            return null;
        }
    }

    @PostMapping
    public ActivoFisico createActivoFisico(@RequestBody ActivoFisico af) {
        try {
            TipoActivo ta = this.tipoActivoService
                    .getTipoActivoById(af.getIdTipoActivo());
            ta.getActivosFisicos().add(af);
            this.tipoActivoService.update(af.getIdTipoActivo(), ta);
            return af;
        } catch (Exception e) {
            logger.error("Create failed: " + e.toString());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivoFisico(@PathVariable(
            value = "id") Long id) {
        try {
            return this.activoFisicoService.delete(id);
        } catch (Exception e) {
            logger.error("Delete failed: " + e.toString());
            return null;
        }
    }

}
