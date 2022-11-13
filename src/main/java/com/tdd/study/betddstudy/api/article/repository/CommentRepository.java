package com.tdd.study.betddstudy.api.article.repository;

import com.tdd.study.betddstudy.api.article.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
