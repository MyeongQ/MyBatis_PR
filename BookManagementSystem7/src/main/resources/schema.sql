CREATE TABLE IF NOT EXISTS BOOK
(
    id SERIAL,
    title VARCHAR(255),
    pagecount INT,
    genre_id INT
);

DROP TABLE IF EXISTS GENRE;

CREATE TABLE IF NOT EXISTS GENRE
(
    genre_id INT,
    name VARCHAR(255)
);

INSERT INTO GENRE (genre_id, name) VALUES (1, 'CS');
INSERT INTO GENRE (genre_id, name) VALUES (2, 'EE');
INSERT INTO GENRE (genre_id, name) VALUES (3, 'CSE');
INSERT INTO GENRE (genre_id, name) VALUES (4, 'MATH');

CREATE TABLE IF NOT EXISTS AUTHOR
(
    id SERIAL,
    name VARCHAR(255),
    book_id INT
);

--INSERT INTO BOOK (title, author, pagecount, genre_id) VALUES ('The C Programming Language', 'K&R', 218, 1);