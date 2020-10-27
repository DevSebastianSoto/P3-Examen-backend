package com.snsm.examen1.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * ActivoFisico
 */
@Entity
@Table(name = "activos_fisicos")
public class ActivoFisico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "cantidad_asignados")
    private int cantidadAsignados;

    @Column(name = "id_tipo_activo")
    private long idTipoActivo;

    @Column(name = "estado")
    private String estado;

    public ActivoFisico() {
    }

    public ActivoFisico(String nombre, String descripcion,
            LocalDate fechaIngreso, int cantidad, int cantidadAsignados,
            long idTipoActivo, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.cantidad = cantidad;
        this.cantidadAsignados = cantidadAsignados;
        this.idTipoActivo = idTipoActivo;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadAsignados() {
        return cantidadAsignados;
    }

    public void setCantidadAsignados(int cantidadAsignados) {
        this.cantidadAsignados = cantidadAsignados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getIdTipoActivo() {
        return idTipoActivo;
    }

}
