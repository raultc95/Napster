package com.Napster.MODEL;

import java.util.List;
import java.util.Objects;

public class User {
    protected int id;
    protected String nombre;
    protected String correo;
    protected String foto;
    List<ListsRep> playlist;

    public User() {
        id=0;
    }

    public User(String nombre, String correo, String foto) {
        this.nombre = nombre;
        this.correo = correo;
        this.foto = foto;
    }

    public User(int id, String nombre, String correo, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.foto = foto;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nombre, user.nombre) && Objects.equals(correo, user.correo);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
