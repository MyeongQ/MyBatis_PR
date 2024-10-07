package com.bookmanagement.model;

public class Genre {
    // Create a POJO

    // field to be created : id, name
    private int genre_id;
    private String name;

    // constructors with parameters
    public Genre(int genre_id, String name) {
        this.genre_id = genre_id;
        this.name = name;
    }

    // getter & setter
    public int getGenreId() {
        return genre_id;
    }

    public void setGenreId(int genreId) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    // .toString method
    @Override
    public String toString() {
        return genre_id + ":" + name;
    }
}
