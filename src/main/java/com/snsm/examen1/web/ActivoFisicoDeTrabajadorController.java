package com.snsm.examen1.web;

import java.util.List;
import com.snsm.examen1.domain.ActivoFisicoDeTrabajador;
import com.snsm.examen1.service.ActivoFisicoDeTrabajadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ActivoFisicoDeTrabajadorController
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/activo-trabajador")
public class ActivoFisicoDeTrabajadorController {

    private static Logger logger =
            LoggerFactory.getLogger(ActivoFisicoDeTrabajadorController.class);

    @Autowired
    private ActivoFisicoDeTrabajadorService aftService;

    @GetMapping("/{id}")
    public ActivoFisicoDeTrabajador getAftById(@PathVariable(
            value = "id") Long id) {
        try {
            return this.aftService.getActivoFisicoDeTrabajadorById(id);
        } catch (Exception e) {
            logger.warn("Get By Id failed: " + e.toString());
            return null;
        }
    }

    @GetMapping
    public List<ActivoFisicoDeTrabajador> getAll() {
        try {
            return aftService.getAllActivoFisicoDeTrabajador();
        } catch (Exception e) {
            logger.warn("Get All failed: " + e.toString());
            return null;
        }
    }

    @PostMapping("/{idTrabajador}/{idActivoFisico}")
    public ActivoFisicoDeTrabajador createActivoFisicoDeTrabajador(@PathVariable(
            value = "idTrabajador") long idTrabajador, @PathVariable(value = "idActivoFisico") long idActivoFisico, @RequestBody ActivoFisicoDeTrabajador aft) {
        try {
            return aftService.create(idTrabajador, idActivoFisico,
                    aft.getFechaAsignacion(), aft.getEstado());
        } catch (Exception e) {
            logger.warn("Create failed: " + e.toString());
            return null;
        }
    }

    @PutMapping("/{id}")
    public ActivoFisicoDeTrabajador closeActivoFisicoDeTrabajador(@PathVariable(
            value = "id") long id) {
        return aftService.close(id);

    }
}
