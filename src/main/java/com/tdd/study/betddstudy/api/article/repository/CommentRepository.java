package com.tdd.study.betddstudy.api.article.repository;

import com.tdd.study.betddstudy.api.article.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join Article a on c.article.id = a.id where a.slug = :slug")
    List<Comment> findCommentListBySlug(String slug);
}
