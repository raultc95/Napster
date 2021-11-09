package com.Napster.MODEL;

public class Album {
    protected int id;
    protected String nombre;
    protected int fecha_publicacion;
    protected String foto;
    protected int n_reproducciones;
    protected Artist artist;



    public Album() {
    }

    public Album(String nombre, int fecha_publicacion, String foto) {
        this.nombre = nombre;
        this.fecha_publicacion = fecha_publicacion;
        this.foto = foto;
    }

    public Album(String nombre, int fecha_publicacion, String foto, int n_reproducciones) {
        this.nombre = nombre;
        this.fecha_publicacion = fecha_publicacion;
        this.foto = foto;
        this.n_reproducciones = n_reproducciones;
    }

    public Album(int id, String nombre, int fecha_publicacion, String foto, int n_reproducciones) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_publicacion = fecha_publicacion;
        this.foto = foto;
        this.n_reproducciones = n_reproducciones;
    }

    public Album(String nombre) {
        this.nombre=nombre;
    }

    public Album(int id, String nombre) {
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


    public int getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(int fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getN_reproducciones() {
        return n_reproducciones;
    }

    public void setN_reproducciones(int n_reproducciones) {
        this.n_reproducciones = n_reproducciones;
    }
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha_publicacion=" + fecha_publicacion +
                ", foto='" + foto + '\'' +
                ", n_reproducciones=" + n_reproducciones +
                '}';
    }
    public String toCombobox(){
        return this.nombre +"."+this.id;
    }
}