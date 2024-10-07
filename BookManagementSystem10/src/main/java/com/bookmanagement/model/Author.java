package com.bookmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR")
public class Author {
    // Create a POJO

    // field to be created : id, name
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Author() {
        // this default construct need by hibernate
    }

    // constructors with parameters
    public Author(int id, String name, Book book) {
        this.id = id;
        this.name = name;
        this.book = book;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // .toString method
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + "'" +
                '}';
    }
}
