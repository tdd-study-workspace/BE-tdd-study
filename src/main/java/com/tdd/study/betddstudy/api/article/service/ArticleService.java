package com.tdd.study.betddstudy.api.article.service;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.dto.ArticleUpdateRequestDto;
import com.tdd.study.betddstudy.api.article.dto.CommentRequest;
import com.tdd.study.betddstudy.api.article.entity.Article;
import com.tdd.study.betddstudy.api.article.entity.Comment;
import com.tdd.study.betddstudy.api.article.entity.Favorite;
import com.tdd.study.betddstudy.api.article.repository.ArticleRepository;
import com.tdd.study.betddstudy.api.article.repository.CommentRepository;
import com.tdd.study.betddstudy.api.tag.service.ArticleTagService;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final UserService userService;
    private final ArticleRepository articleRepository;
    private final ArticleTagService articleTagService;
    private final CommentRepository commentRepository;

    @Transactional
    public Article addArticle(ArticleRequestDto articleRequestDto) {
        Article.ArticleBuilder builder = Article.builder();
        builder.title(articleRequestDto.getTitle());
        builder.slug(this.makeSlug(articleRequestDto.getTitle()));
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
        if (articleRepository.existsById(article.getId())) {
            articleRepository.delete(article);
        } else {
            return false;
        }
        return true;
    }

    public List<Article> getArticleFeed(int offset, int limit) {
        return articleRepository.findByOffsetAndLimit(offset, limit);
    }

    private String makeSlug(String title) {
        return title.toLowerCase().replaceAll(" ", "-");
    }

    public Article getArticleBySlug(String slug) {
        return articleRepository.findBySlug(slug);
    }

    @Transactional
    public Article updateArticleBySlug(String slug, ArticleUpdateRequestDto articleUpdateRequestDto) {
        Article articleBySlug = getArticleBySlug(slug);
        Article.ArticleBuilder builder = Article.builder();
        builder.description(articleUpdateRequestDto.getDescription());
        builder.body(articleUpdateRequestDto.getBody());
        builder.title(articleUpdateRequestDto.getTitle());
        builder.slug(makeSlug(articleUpdateRequestDto.getTitle()));
        articleBySlug.update(builder.build());
        return articleRepository.save(articleBySlug);
    }

    @Transactional
    public Comment addComment(String slug, CommentRequest commentRequest,@Nullable User user) {
        Article article = this.getArticleBySlug(slug);
        Comment.CommentBuilder builder = Comment.builder();
        builder.article(article);
        builder.author(user);
        builder.body(commentRequest.getBody());
        return commentRepository.save(builder.build());
    }

    @Transactional
    public void deleteComment(String slug, Long id) {
        commentRepository.deleteById(id);
    }
    public List<Comment> getCommentByArticleSlug(String slug) {
        return commentRepository.findCommentListBySlug(slug);
    }

    public Article favoriteArticle(String slug, Long userId) {
        Article article = this.getArticleBySlug(slug);
        User user = this.userService.getUser(userId);

        if(Objects.nonNull(article.getFavoriteList())
                && article.getFavoriteList().stream().map(Favorite::getUser).anyMatch(user1 -> user1.getEmail().equals(user.getEmail()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Favorite favorite = Favorite.builder().article(article).user(user).build();
        List<Favorite> list = new ArrayList<>();
        list.add(favorite);
        Article.ArticleBuilder builder = Article.builder().favoriteList(list);

        article.update(builder.build());

        return articleRepository.save(article);
    }
}

