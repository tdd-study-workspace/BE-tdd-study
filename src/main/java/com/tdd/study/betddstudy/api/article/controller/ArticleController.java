package com.tdd.study.betddstudy.api.article.controller;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.entity.Article;
import com.tdd.study.betddstudy.api.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public void addArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        articleService.addArticle(articleRequestDto);
    }

    @GetMapping("/article")
    public List<Article> getArticle() {
        return articleService.getArticle();
    }
}
