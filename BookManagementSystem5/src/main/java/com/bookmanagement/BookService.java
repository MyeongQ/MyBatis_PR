package com.bookmanagement;

import com.bookmanagement.mapper.BookMapper;
import com.bookmanagement.mapper.GenreMapper;
import com.bookmanagement.model.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Scanner;

public class BookService {
    private final Scanner input = new Scanner(System.in);
    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;

    public BookService(SqlSession session) {
        bookMapper = session.getMapper(BookMapper.class);
        genreMapper = session.getMapper(GenreMapper.class);
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

        bookMapper.saveBook(newBook);
    }

    // bring all listed genres
    public List<Genre> getAllGenres() {
        return genreMapper.getAllGenres();
    }

    // bring all listed books
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    // find book by id
    public Book getBookByID(int id) {
        Book book = bookMapper.getBook(id);
        if (book == null) {
            System.out.println("There is not a book with this id: " + id);
        }
        return book;
    }

    // find genre by id
    public Genre getGenreByID(int id) {
        return(genreMapper.getGenre(id));
    }

    // method to delete book by id:
    public void deleteBookByID(int id) {
        bookMapper.deleteBook(id);
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
            bookMapper.updateBook(existBook);
        }
    }
}
