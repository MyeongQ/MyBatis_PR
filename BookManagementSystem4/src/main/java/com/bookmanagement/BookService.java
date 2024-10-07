package com.bookmanagement;

import com.bookmanagement.model.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Scanner;

public class BookService {
    private final Scanner input = new Scanner(System.in);
    private final SqlSession session;

    public BookService(SqlSession session) {
        this.session = session;
    }

    // Method to register book:
    public void saveBook() {
        System.out.println("Title: ");
        String title = input.nextLine();

        System.out.println("Author: ");
        String author = input.nextLine();

        System.out.println("Genre: ");
        System.out.print("(");
        List<Genre> genres = getAllGenres();
        for (Genre genre: genres) {
            System.out.print(genre + ", ");
        }
        System.out.println("Number Only)");
        int genreid = input.nextInt();

        System.out.println("Page Count: ");
        int pagecount = input.nextInt();

        input.nextLine(); // consume new line

        // After getting all data from the user we create a new object using constructor
        Genre genre = getGenreByID(genreid);
        Book newBook = new Book(0, title, author, pagecount, genre);

        session.insert("BookMapper.insert", newBook);
    }

    // bring all listed Genres
    public List<Genre> getAllGenres() {
        return(session.selectList("GenreMapper.getAll"));
    }

    // bring all listed books
    public List<Book> getAllBooks() {
        return(session.selectList("BookMapper.getAll"));
    }

    // find book by id
    public Book getBookByID(int id) {
        return(session.selectOne("BookMapper.getById", id));
    }

    // get genre by id
    public Genre getGenreByID(int id) {
        return(session.selectOne("GenreMapper.getById", id));
    }

    // method to delete book by id:
    public void deleteBookByID(int id) {
        session.delete("BookMapper.deleteById", id);
    }

    // method to update:
    public void updateBook(int id) {
        // find book by id from the table:
        Book existBook = getBookByID(id);

        if (existBook == null) {
            System.out.println("Book with ID: " + id + "is not found.");
        } else {
            // Ask all data from the user:
            System.out.println("Title: ");
            String title = input.nextLine();

            System.out.println("Author: ");
            String author = input.nextLine();

            System.out.println("Genre: ");
            System.out.print("(");
            List<Genre> genres = getAllGenres();
            for (Genre genre: genres) {
                System.out.print(genre + ", ");
            }
            System.out.println("Number Only)");
            int genreid = input.nextInt();

            System.out.println("Page Count: ");
            int pagecount = input.nextInt();

            // Start updating existing book:
            existBook.setTitle(title);
            existBook.setAuthor(author);
            Genre genre = getGenreByID(genreid);
            existBook.setGenre(genre);
            existBook.setPagecount(pagecount);

            // We can leave id unchanged.
            session.update("BookMapper.update", existBook);
        }
    }
}