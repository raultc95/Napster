package com.Napster.MODEL;

public class Song {
    protected int id;
    protected String nombre;
    protected int duracion;
    protected int nreproducciones;
    protected Album album;
    protected Genre genre;


    public Song() {
    }

    public Song(int id) {
        this.id = id;
    }

    public Song(String nombre) {
        this.nombre = nombre;
    }

    public Song(String nombre, int duracion, int nreproducciones) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.nreproducciones = nreproducciones;
    }

    public Song(int id, String nombre, int duracion, int nreproducciones) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.nreproducciones = nreproducciones;
    }

    public Song(int id, String nombre) {
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }


    public int getNreproducciones() {
        return nreproducciones;
    }

    public void setNreproducciones(int nreproducciones) {
        this.nreproducciones = nreproducciones;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", nreproducciones=" + nreproducciones +
                '}';
    }
    /**
     * Pasa un objeto a string, para asi recoger los datos del ComboBox
     */
    public String toCombobox() {
        return this.nombre + "." + this.id;
    }


}
