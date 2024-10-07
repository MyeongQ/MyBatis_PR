package com.bookmanagement;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookmanagement.model.*;

import java.util.List;
import java.util.Scanner;

public class BookService {
    private final Scanner input = new Scanner(System.in);
    private final EntityManager entityManager;

    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
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
        int genreId = input.nextInt();

        Genre genre = getGenreByID(genreId);

        System.out.println("Page Count: ");
        int pageCount = input.nextInt();

        input.nextLine(); // consume new line

        // After getting all data from the user we create a new object using constructor
        Book newBook = new Book(0, title, pageCount, genre);

        boolean flag = true;
        while (flag) {
            System.out.println("Author(Press [Enter] to finish): ");
            String author = input.nextLine();
            if (author.isEmpty()) {
                flag = false;
            } else {
                Author newAuthor = new Author();
                newAuthor.setId(0);
                newAuthor.setName(author);
                newBook.addAuthor(newAuthor);
            }
        }
        entityManager.persist(newBook);
    }

    // bring all listed books
    public List<Book> getAllBooks() {
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN Author a ON b.id = a.book.id JOIN Genre g ON b.genre.id = g.id");
        return(query.getResultList());
    }

    public List<Genre> getAllGenres() {
        Query query = entityManager.createQuery("SELECT g FROM Genre g");
        return(query.getResultList());
    }

    // bring all listed books
    public List<Author> getAuthorsByBookID(int bookid) {
        Integer bookKey = bookid;
        Query query = entityManager.createQuery("SELECT a FROM Author a where a.book.id = :bookKey");
        query.setParameter("bookKey", bookKey);
        return(query.getResultList());
    }

    public Author getAuthorByID(int id) {
        Integer primaryKey = id;
        Author author = entityManager.find(Author.class, primaryKey);

        if (author == null) {
            System.out.println("There is no such a book with this id: " + id);
        }
        return author;
    }

    // find book by id
    public Book getBookByID(int id) {
        Integer primaryKey = id;
        Book book = entityManager.find(Book.class, primaryKey);

        if (book == null) {
            System.out.println("There is no such a book with this id: " + id);
        }
        return book;
    }

    public Genre getGenreByID(int id) {
        Integer primaryKey = id;
        Genre genre = entityManager.find(Genre.class, primaryKey);
        if (genre == null) {
            System.out.println("There is no such a genre with this id: " + id);
        }
        return genre;
    }

    // method to delete book by id:
    public void deleteBookByID(int id) {
        Integer primaryKey = id;
        Book reference = entityManager.getReference(Book.class, primaryKey);
        entityManager.remove(reference);
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
            int genreId = input.nextInt();

            Genre genre = getGenreByID(genreId);

            System.out.println("Page Count: ");
            int pagecount = input.nextInt();

            // Start updating existing book:
            existBook.setTitle(title);
            existBook.setGenre(genre);
            existBook.setPagecount(pagecount);

            // We can leave id unchanged.
            entityManager.merge(existBook);
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
            entityManager.merge(existAuthor);
        }
    }
}
