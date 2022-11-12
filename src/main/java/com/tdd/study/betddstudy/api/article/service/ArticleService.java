package com.tdd.study.betddstudy.api.article.service;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.dto.ArticleUpdateRequestDto;
import com.tdd.study.betddstudy.api.article.entity.Article;
import com.tdd.study.betddstudy.api.article.repository.ArticleRepository;
import com.tdd.study.betddstudy.api.tag.service.ArticleTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.tdd.study.betddstudy.api.article.entity.Article.*;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleTagService articleTagService;

    @Transactional
    public Article addArticle(ArticleRequestDto articleRequestDto) {
        ArticleBuilder builder = builder();
        builder.title(articleRequestDto.getTitle());
        builder.slug(makeSlug(articleRequestDto.getTitle()));
        builder.description(articleRequestDto.getDescription());
        builder.body(articleRequestDto.getBody());
        if (Objects.nonNull(articleRequestDto.getTagList())) {
        }
        return articleRepository.save(builder.build());
    }

    public List<Article> getArticle() {
        return articleRepository.findAll();
    }

    public boolean deleteArticle(Article article) {
        if(articleRepository.existsById(article.getId())) {
            articleRepository.delete(article);
        } else {
            return false;
        }
        return true;
    }

    private String makeSlug(String title) {
        return title.replaceAll(" ", "-");
    }

    public Article getArticleBySlug(String slug) {
        return articleRepository.findBySlug(slug);
    }

    @Transactional
    public Article updateArticleBySlug(String slug, ArticleUpdateRequestDto articleUpdateRequestDto) {
        Article articleBySlug = getArticleBySlug(slug);
        ArticleBuilder builder = builder();
        builder.description(articleUpdateRequestDto.getDescription());
        builder.body(articleUpdateRequestDto.getBody());
        builder.title(articleUpdateRequestDto.getTitle());
        builder.slug(makeSlug(articleUpdateRequestDto.getTitle()));
        articleBySlug.update(builder.build());
        return articleRepository.save(articleBySlug);
    }
}

