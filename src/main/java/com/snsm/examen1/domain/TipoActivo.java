package com.snsm.examen1.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoActivo
 */
@Entity
@Table(name = "tipos_activo")
public class TipoActivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_activo", referencedColumnName = "id")
    List<ActivoFisico> activosFisicos = new ArrayList<>();

    public TipoActivo() {
    }

    public TipoActivo(String nombre, String descripcion, String estado) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ActivoFisico> getActivosFisicos() {
        return activosFisicos;
    }

    public void setActivosFisicos(List<ActivoFisico> activosFisicos) {
        this.activosFisicos = activosFisicos;
    }


    @Override
    public String toString() {
        return "TipoActivo [descripcion=" + descripcion + ", estado=" + estado
                + ", id=" + id + ", nombre=" + nombre + "]";
    }
}
