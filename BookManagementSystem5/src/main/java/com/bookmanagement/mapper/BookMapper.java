package com.bookmanagement.mapper;

import com.bookmanagement.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    final String getAll = "SELECT id, title, author, pagecount, genre_id FROM Book";
    final String getById = "SELECT id, title, author, pagecount, genre_id FROM Book WHERE id = #{id}";
    final String deleteById = "DELETE from BOOK WHERE ID = #{id}";
    final String insert = "INSERT INTO BOOK (TITLE, AUTHOR, GENRE_ID, PAGECOUNT ) VALUES (#{title}, #{author}, #{genre.genre_id}, #{pagecount})";
    final String update = "UPDATE BOOK SET TITLE = #{title}, AUTHOR = #{author}, GENRE_ID = #{genre.genre_id}, PAGECOUNT = #{pagecount} WHERE ID = #{id}";

    @Select(getAll)
    @Results({
            @Result(column = "ID", property = "id", id = true),
            @Result(column = "TITLE", property = "title"),
            @Result(column = "AUTHOR", property = "author"),
            @Result(column = "PAGECOUNT", property = "pagecount"),
            @Result(column = "GENRE_ID", property = "genre", javaType = Genre.class,
                    one = @One(select = "com.bookmanagement.mapper.GenreMapper.getGenre"))
    })
    List<Book> getAllBooks();

    @Select(getById)
    @Results({
            @Result(column = "ID", property = "id", id = true),
            @Result(column = "TITLE", property = "title"),
            @Result(column = "AUTHOR", property = "author"),
            @Result(column = "PAGECOUNT", property = "pagecount"),
            @Result(column = "genre_id", property = "genre", javaType = Genre.class,
                    one = @One(select = "com.bookmanagement.mapper.GenreMapper.getGenre"))
    })
    Book getBook(int id);

    @Insert(insert)
    public void saveBook(Book book);

    @Delete(deleteById)
    public void deleteBook(int id);

    @Update(update)
    public void updateBook(Book book);
}
