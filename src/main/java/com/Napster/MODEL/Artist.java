package com.Napster.MODEL;

public class Artist  {
    protected int id;
    protected String nombre;
    protected String nacionalidad;
    protected String foto;
    protected Artist a;

    public Artist getA() {
        return a;
    }

    public void setA(Artist a) {
        this.a = a;
    }

    public Artist() {
    }
    public Artist(int id, String nombre) {
        this.id=id;
        this.nombre = nombre;
    }

    public Artist(String nombre, String nacionalidad, String foto) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
    }

    public Artist(int id, String nombre, String nacionalidad, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
    }

    public Artist(int id) {
        this.id=id;
    }

    public Artist(int id, String nombre, String nacionalidad) {
        this.id=id;
        this.nombre = nombre;
        this.nacionalidad=nacionalidad;

    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", nombre=" + nombre +  ", nacionalidad=" + nacionalidad +  ", foto=" + foto;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String toCombobox(){
        return this.nombre +"."+this.id;
    }
}
