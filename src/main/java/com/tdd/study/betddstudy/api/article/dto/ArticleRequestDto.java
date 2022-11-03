package com.tdd.study.betddstudy.api.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleRequestDto {

    private String title;
    private String description;
    private String body;
    private List<String> tagList = new ArrayList<>();
}
