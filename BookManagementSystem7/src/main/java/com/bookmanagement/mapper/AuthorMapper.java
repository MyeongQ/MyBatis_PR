package com.bookmanagement.mapper;

import com.bookmanagement.model.Author;
import com.bookmanagement.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorMapper {
    final String getAll = "SELECT * FROM Author";
    final String getById = "SELECT * FROM Author WHERE ID = #{id}";
    final String getByBookId = "SELECT * FROM Author WHERE BOOK_ID = #{id}";
    final String insert = "INSERT INTO AUTHOR (NAME, BOOK_ID) VALUES (#{name}, #{book_id});";
    final String update = "UPDATE AUTHOR SET NAME = #{name}, BOOK_ID = #{book_id} WHERE ID = #{id}";

    @Select(getAll)
    List<Author> getAllAuthors();

    @Select(getById)
    Author getAuthor(int id);

    @Select(getByBookId)
    List<Author> getAuthorsByBookId(int book_id);

    @Insert(insert)
    public void saveAuthor(Author author);

    @Update(update)
    public void updateAuthor(Author author);
}
