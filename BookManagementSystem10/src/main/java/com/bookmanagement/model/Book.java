package com.bookmanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class Book {
    // Create a POJO

    // field to be created : id, title, author, genre, pageCount
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int pagecount;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    public Book() {
        // this default construct need by hibernate
    }

    // constructors with parameters
    public Book(int id, String title, int pagecount, Genre genre) {
        this.id = id;
        this.title = title;
        this.pagecount = pagecount;
        this.genre = genre;
    }

    // getter &  setter
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public List<Author> getAuthors() { return authors; }

    public void addAuthor(Author author) {
        authors.add(author);
        author.setBook(this);
    }

    // .toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", pagecount=" + pagecount +
                ", authors=" + authors +
                '}';
    }
}
