package com.snsm.examen1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.snsm.examen1.domain.Trabajador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TrabajadorServiceTests
 */
@SpringBootTest
public class TrabajadorServiceTests {

    public static Logger logger =
            LoggerFactory.getLogger(TrabajadorServiceTests.class);

    @Autowired
    private TrabajadorService trabajadorService;

    private List<Trabajador> trabajadores;

    private List<Long> createdIds;

    @BeforeEach
    void setup() {
        logger.info("Running setup");
        createdIds = new ArrayList<Long>();
    }

    @Test
    void getTrabajadoresTest() {
        Trabajador trb = new Trabajador("Santiago", "Soto Perez",
                LocalDate.of(2005, 1, 7), "Inactivo");
        trabajadorService.create(trb);
        createdIds.add(trb.getId());
        trabajadores = trabajadorService.getAllTrabajador();
        assert (trabajadores.size() > 0);
    }

    @Test
    void createTrabajadorTest() {
        logger.info("Creating trabajador");
        Trabajador trb = new Trabajador("Santiago", "Soto Perez",
                LocalDate.of(2005, 1, 7), "Inactivo");

        assertEquals(0, trb.getId());
        trabajadorService.create(trb);

        createdIds.add(trb.getId());

        assert trb.getId() > 0;
    }

    @Test
    void updateTrabajadorTest() {
        String n1 = "Santiago";
        String n2 = "Shanti";

        Trabajador trb = new Trabajador(n1, "Soto Perez",
                LocalDate.of(2005, 1, 7), "Inactivo");
        trabajadorService.create(trb);
        createdIds.add(trb.getId());

        trabajadorService.update(trb.getId(), new Trabajador(n2,
                "Soto Madrigal", trb.getFechaNacimiento(), "Activo"));
        assertNotEquals(n1, n2, "The name changed");
    }

    @AfterEach
    void teardown() {
        for (long id : createdIds) {
            logger.warn("Removed Trabajador #" + id);
            trabajadorService.delete(id);
        }
    }

}
