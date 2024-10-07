package com.bookmanagement.model;

public class Author {
    // Create a POJO

    // field to be created : id, name, book_id
    private int id;
    private String name;
    private int book_id;

    public Author() {

    }

    // constructors with parameters
    public Author(int id, String name, int book_id) {
        this.id = id;
        this.name = name;
        this.book_id = book_id;
    }

    // getter & setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getBookId() { return book_id; }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    // .toString method
    @Override
    public String toString()
    {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", book_id=" + book_id +
                '}';
    }
}
