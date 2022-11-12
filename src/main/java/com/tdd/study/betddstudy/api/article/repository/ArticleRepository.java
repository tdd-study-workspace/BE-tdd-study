package com.tdd.study.betddstudy.api.article.repository;

import com.tdd.study.betddstudy.api.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article deleteArticleById(Long id);

    @Query(value = "select * from Article a limit ?2 offset ?1", nativeQuery = true)
    List<Article> findByOffsetAndLimit(int offset, int limit);

    Article findBySlug(String slug);
}
