CREATE TABLE IF NOT EXISTS BOOK
(
    id SERIAL,
    title VARCHAR(255),
    genre_id INT,
    pagecount INT
);

CREATE TABLE IF NOT EXISTS AUTHOR
(
    id SERIAL,
    name VARCHAR(255),
    book_id INT
);

DROP TABLE IF EXISTS GENRE;

CREATE TABLE IF NOT EXISTS GENRE
(
    id INT,
    name VARCHAR(255)
);

INSERT INTO GENRE (id, name) VALUES (1, 'CS');
INSERT INTO GENRE (id, name) VALUES (2, 'EE');
INSERT INTO GENRE (id, name) VALUES (3, 'CSE');
INSERT INTO GENRE (id, name) VALUES (4, 'MATH');

--INSERT INTO BOOK (title, author, genre, pagecount) VALUES ('The C Programming Language', 'K&R', 'CS', 218);