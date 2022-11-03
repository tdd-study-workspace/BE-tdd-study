package com.tdd.study.betddstudy.api.article.repository;

import com.tdd.study.betddstudy.api.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select * from Article a order by a.id offset ?1 limit ?2", nativeQuery = true)
    List<Article> findByOffsetAndLimit(int offset, int limit);
}
