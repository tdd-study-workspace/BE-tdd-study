package com.tdd.study.betddstudy.api.article.controller;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.dto.ArticleUpdateRequestDto;
import com.tdd.study.betddstudy.api.article.dto.CommentRequest;
import com.tdd.study.betddstudy.api.article.entity.Article;
import com.tdd.study.betddstudy.api.article.entity.Comment;
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

    @GetMapping("/article/{slug}")
    public Article getArticleBySlug(@PathVariable String slug) {
        return articleService.getArticleBySlug(slug);
    }

    @PutMapping("/article/{slug}")
    public void updateArticleBySlug(@PathVariable String slug, @RequestBody(required = false) ArticleUpdateRequestDto articleUpdateRequestDto) {
        articleService.updateArticleBySlug(slug, articleUpdateRequestDto);
    }

    @PostMapping("/articles/{slug}/comments")
    public void addComment(@PathVariable String slug, @RequestBody CommentRequest commentRequest) {
        articleService.addComment(slug, commentRequest, null);
    }

    @DeleteMapping("/articles/{slug}/comments/{id}")
    public void deleteComment(@PathVariable String slug, @PathVariable Long id) {
        articleService.deleteComment(slug, id);
    }

    @GetMapping("/articles/{slug}/comments")
    public List<Comment> getCommentByArticleSlug(@PathVariable String slug) {
        return articleService.getCommentByArticleSlug(slug);
    }
}
