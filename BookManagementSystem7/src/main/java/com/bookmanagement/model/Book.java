package com.bookmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    // Create a POJO

    // field to be created : id, title, author, genre, pagecount
    private int id;
    private String title;
    private int pagecount;
    private Genre genre;
    private List<Author> authors = new ArrayList<>();

    public Book() {

    }

    // constructors with parameters
    public Book(int id, String title, int pagecount, Genre genre) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.pagecount = pagecount;
        this.genre = genre;
    }

    // getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() { return authors; }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    // .toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pagecount=" + pagecount +
                ", genre=" + genre +
                ", authors='" + authors + '\'' +
                '}';
    }
}
