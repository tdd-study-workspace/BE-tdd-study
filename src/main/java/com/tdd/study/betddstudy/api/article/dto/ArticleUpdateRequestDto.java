package com.tdd.study.betddstudy.api.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleUpdateRequestDto {
    private String title;
    private String description;
    private String body;
}
