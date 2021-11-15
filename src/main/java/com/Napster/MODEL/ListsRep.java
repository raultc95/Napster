package com.Napster.MODEL;


public class ListsRep {
    protected int id;
    protected String nombre;
    protected String descripcion;
    protected String creador;


    public ListsRep() {
    }


    public ListsRep(String nombre, String descripcion, String creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
    }

    public ListsRep(int id, String nombre, String descripcion, String creador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
    }

    public ListsRep(String nombre) {
        this.nombre = nombre;
    }

    public ListsRep(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    @Override
    public String toString() {
        return "ListsRep{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", creador='" + creador + '\'' +
                '}';
    }
}
