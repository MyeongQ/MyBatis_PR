package com.bookmanagement;

import com.bookmanagement.mapper.*;
import com.bookmanagement.model.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Scanner;

public class BookService {
    private final Scanner input = new Scanner(System.in);
    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;

    public BookService(SqlSession session) {
        bookMapper = session.getMapper(BookMapper.class);
        genreMapper = session.getMapper(GenreMapper.class);
        authorMapper = session.getMapper(AuthorMapper.class);
    }

    // Method to register book:
    public void saveBook() {
        System.out.println("Title: ");
        String title = input.nextLine();

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
        Book newBook = new Book(0, title, pagecount, genre);

        boolean flag = true;
        while (flag) {
            System.out.println("Author(Press [Enter] to finish): ");
            String author = input.nextLine();
            if (author.isEmpty()) {
                flag = false;
            } else {
                Author newAuthor = new Author(0, author, newBook.getId());
                newAuthor.setId(0);
                newAuthor.setName(author);
                authorMapper.saveAuthor(newAuthor);
                newBook.addAuthor(newAuthor);
            }
        }

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

    public Author getAuthorByID(int id) {
        return(authorMapper.getAuthor(id));
    }

    public List<Author> getAuthorsByBookID(int book_id) {
        return(authorMapper.getAuthorsByBookId(book_id));
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
            Genre genre = getGenreByID(genreid);
            existBook.setGenre(genre);
            existBook.setPagecount(pagecount);

            // We can leave id unchanged.
            bookMapper.updateBook(existBook);
        }
    }

    public void updateAuthor(int id) {
        // find book by id from the table:
        Author existAuthor = getAuthorByID(id);

        if (existAuthor == null) {
            System.out.println("Author with ID: " + id + "is not found.");
        } else {
            // Ask all data from the user:
            System.out.println("Name: ");
            String name = input.nextLine();

            // Start updating existing book:
            existAuthor.setName(name);

            // We can leave id unchanged.
            authorMapper.updateAuthor(existAuthor);
        }
    }
}
