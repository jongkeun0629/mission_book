package com.jongkeun.mission_book.repository;

import com.jongkeun.mission_book.model.Author;
import com.jongkeun.mission_book.model.Dashboard;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DashboardRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Dashboard> mapper = (resultSet, rowNum) ->
            Dashboard.builder()
                    .authorId(resultSet.getInt("author_id"))
                    .authorName(resultSet.getString("author_name"))
                    .bookCount(resultSet.getInt("book_count"))
                    .build();

    public List<Dashboard> findAll(){
        return jdbcTemplate.query("SELECT * FROM author_book_count ORDER BY author_name", mapper);
    }
}
