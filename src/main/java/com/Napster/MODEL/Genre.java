package com.Napster.MODEL;

public class Genre {
    protected int id;
    protected String name;

    public Genre() {
    }
    public Genre(int id){
        this.id=id;
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public String toCombobox(){
        return this.name +"."+this.id;
    }
}
