package com.snsm.examen1.web;

import java.util.List;
import com.snsm.examen1.domain.Trabajador;
import com.snsm.examen1.service.TrabajadorService;
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
 * TrabajadorController
 */
@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    private static Logger logger =
            LoggerFactory.getLogger(TrabajadorController.class);

    @GetMapping("/{id}")
    public Trabajador getDatoById(@PathVariable(value = "id") Long id) {
        try {
            return this.trabajadorService.getTrabajadorById(id);
        } catch (Exception e) {
            logger.warn("Get failed: " + e.toString());
            return null;
        }
    }

    @GetMapping
    public List<Trabajador> getAllTrabajador() {
        try {
            logger.info("Get All");
            return this.trabajadorService.getAllTrabajador();
        } catch (Exception e) {
            logger.warn("Get failed: " + e.toString());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Trabajador updateTrabajador(@PathVariable(value = "id") Long id,
                                       @RequestBody Trabajador ta) {
        try {
            logger.info("Update: " + ta.toString());
            return this.trabajadorService.update(id, ta);
        } catch (Exception e) {
            logger.warn("Put failed: " + e.toString());
            return null;
        }
    }

    @PostMapping
    public Trabajador createTrabajador(@RequestBody Trabajador ta) {
        try {
            logger.info("Create: " + ta.toString());
            return this.trabajadorService.create(ta);
        } catch (Exception e) {
            logger.warn("Post failed: " + e.toString());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrabajador(@PathVariable(
            value = "id") Long id) {
        try {
            logger.info("Delete: Tipo Activo #" + id);
            return this.trabajadorService.delete(id);
        } catch (Exception e) {
            logger.warn("Delete failed: " + e.toString());
            return null;
        }
    }

}
