package com.tdd.study.betddstudy.api.tag.repository;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {

}
