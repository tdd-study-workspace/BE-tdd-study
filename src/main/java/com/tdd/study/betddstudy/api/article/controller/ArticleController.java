package com.tdd.study.betddstudy.api.article.controller;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public void addArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        articleService.addArticle(articleRequestDto);
    }
}
