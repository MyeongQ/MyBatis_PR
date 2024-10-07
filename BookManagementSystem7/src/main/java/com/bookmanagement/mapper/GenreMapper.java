package com.bookmanagement.mapper;

import com.bookmanagement.model.Genre;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GenreMapper {
    final String getAll = "SELECT * FROM GENRE";
    final String getById = "SELECT * FROM GENRE WHERE GENRE_ID = #{id}";

    @Select(getAll)
    List<Genre> getAllGenres();

    @Select(getById)
    Genre getGenre(int id);
}
