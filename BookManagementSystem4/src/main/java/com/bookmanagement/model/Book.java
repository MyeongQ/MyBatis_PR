package com.bookmanagement.model;

public class Book {
    // Create a POJO

    // field to be created : id, title, author, genre, pagecount
    private int id;
    private String title;
    private String author;
    private int pagecount;
    private Genre genre;

    public Book() {

    }

    // constructors with parameters
    public Book(int id, String title, String author, int pagecount, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    // .toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", pagecount=" + pagecount +
                '}';
    }
}
