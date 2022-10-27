package com.tdd.study.betddstudy.api.tag.controller;

import com.tdd.study.betddstudy.api.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleTagController {

    private final ArticleService articleService;


}
