package com.jongkeun.mission_book.repository;

import com.jongkeun.mission_book.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;
    
    private final RowMapper<Book> mapper = (resultSet, rowNum) -> 
            Book.builder()
                    .id(resultSet.getInt("id"))
                    .title(resultSet.getString("title"))
                    .authorId(resultSet.getInt("author_id"))
                    .publishedDate(resultSet.getDate("published_date").toLocalDate())
                    .build();

    public List<Book> findAll(){
        return jdbcTemplate.query("SELECT * FROM books ORDER BY title", mapper);
    }

    public Book findById(int id){
        return jdbcTemplate.queryForObject(
                "SELECT * FROM books WHERE id = ?", mapper, id
        );
    }

    public int save(Book book) {
        return jdbcTemplate.update(
                "INSERT INTO books (title, author_id, published_date) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthorId(), book.getPublishedDate()
        );
    }

    public int update(Book book) {
        return jdbcTemplate.update(
                "UPDATE books SET title = ?, published_date = ? WHERE id = ?",
                book.getTitle(), book.getPublishedDate(), book.getId()
        );
    }

    public int delete(int id){
        return jdbcTemplate.update(
                "DELETE FROM books WHERE id = ?", id
        );
    }
}
