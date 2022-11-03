package com.tdd.study.betddstudy.api.article.repository;

import com.tdd.study.betddstudy.api.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article deleteArticleById(Long id);
}
