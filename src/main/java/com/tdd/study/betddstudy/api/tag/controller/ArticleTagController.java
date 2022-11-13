package com.tdd.study.betddstudy.api.tag.controller;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import com.tdd.study.betddstudy.api.tag.service.ArticleTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleTagController {

    private final ArticleTagService articleTagService;

    @GetMapping("tags")
    public List<ArticleTag> getTags() {
        return articleTagService.getTags();
    }
}
