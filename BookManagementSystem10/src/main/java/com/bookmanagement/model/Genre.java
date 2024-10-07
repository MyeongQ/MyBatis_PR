package com.bookmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "GENRE")
public class Genre {
    // Create a POJO

    // field to be created : id, name
    @Id
    private int id;
    private String name;

    public Genre() {
        // this default construct need by hibernate
    }

    // constructors with parameters
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter &  setter
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

    // .toString method
    @Override
    public String toString() { return id + ":" + name; }
}
