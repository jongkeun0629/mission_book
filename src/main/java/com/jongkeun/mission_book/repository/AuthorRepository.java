package com.jongkeun.mission_book.repository;

import com.jongkeun.mission_book.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Author> mapper = (resultSet, rowNum) ->
            Author.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();

    public List<Author> findAll(){
        return jdbcTemplate.query("SELECT * FROM authors ORDER BY name", mapper);
    }

    public Author findById(int id){
        return jdbcTemplate.queryForObject(
                "SELECT * FROM authors WHERE id = ?", mapper, id
        );
    }

    public int save(Author author) {
        return jdbcTemplate.update(
                "INSERT INTO authors (name) VALUES (?)", author.getName()
        );
    }

    public int update(Author author) {
        return jdbcTemplate.update(
                "UPDATE authors SET name = ? WHERE id = ?", author.getName(), author.getId()
        );
    }

    public int delete(int id){
        return jdbcTemplate.update(
                "DELETE FROM authors WHERE id = ?", id
        );
    }
}
