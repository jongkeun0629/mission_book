package com.jongkeun.mission_book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dashboard {
    private Integer authorId;
    private String authorName;
    private Integer bookCount;
}
