package com.tdd.study.betddstudy.article;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.dto.ArticleUpdateRequestDto;
import com.tdd.study.betddstudy.api.article.dto.CommentRequest;
import com.tdd.study.betddstudy.api.article.entity.Article;
import com.tdd.study.betddstudy.api.article.entity.Article.ArticleBuilder;
import com.tdd.study.betddstudy.api.article.entity.Comment;
import com.tdd.study.betddstudy.api.article.repository.ArticleRepository;
import com.tdd.study.betddstudy.api.article.repository.CommentRepository;
import com.tdd.study.betddstudy.api.article.service.ArticleService;
import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;

    @DisplayName("Article 등록 Tag 없는 경우")
    @Transactional
    @ParameterizedTest
    @CsvSource({
        "testTitle, testDescription, testBody"
    })
    void addArticleTest(String title, String description, String body) {
        //given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(title, description, body, null);

        //when
        Article article = articleService.addArticle(articleRequestDto);

        //then
        assertThat(article).isEqualTo(articleRepository.findById(article.getId()).get());
    }

    @DisplayName("Article List 조회")
    @Transactional
    @Test
    void getArticleListTest() {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("title1", "description", "body", null);
        ArticleRequestDto articleRequestDto2 = new ArticleRequestDto("title2", "description", "body", null);
        ArticleRequestDto articleRequestDto3 = new ArticleRequestDto("title3", "description", "body", null);

        articleService.addArticle(articleRequestDto1);
        articleService.addArticle(articleRequestDto2);
        articleService.addArticle(articleRequestDto3);

        //when
        List<Article> article = articleService.getArticle();
        System.out.println(article.size());
        //then
        assertThat(article.size()).isEqualTo(3);
    }

    @DisplayName("Article 삭제 성공 테스트")
    @Transactional
    @ParameterizedTest
    @CsvSource({
            "testTitle, testDescription, testBody"
    })
    void deleteArticleSuccessTest(String title, String description, String body) {
        //given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(title, description, body, null);

        Article article = articleService.addArticle(articleRequestDto);

        //when
        boolean result = articleService.deleteArticle(article);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("Article 삭제 실패 테스트")
    @Transactional
    @ParameterizedTest
    @CsvSource({
            "testTitle, testDescription, testBody"
    })
    void deleteArticleFailTest(String title, String description, String body) {
        //given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(title, description, body, null);

        Article article = articleService.addArticle(articleRequestDto);
        Article obj = Article.builder().id(-1L).build();

        //when
        boolean result = articleService.deleteArticle(obj);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("Ariticle list feed test (offset)")
    @Test
    @Transactional
    void getArticleListFeedTest() {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("title1", "description", "body", null);
        ArticleRequestDto articleRequestDto2 = new ArticleRequestDto("title2", "description", "body", null);
        ArticleRequestDto articleRequestDto3 = new ArticleRequestDto("title3", "description", "body", null);
        ArticleRequestDto articleRequestDto4 = new ArticleRequestDto("title4", "description", "body", null);
        ArticleRequestDto articleRequestDto5 = new ArticleRequestDto("title5", "description", "body", null);

        articleService.addArticle(articleRequestDto1);
        articleService.addArticle(articleRequestDto2);
        articleService.addArticle(articleRequestDto3);
        articleService.addArticle(articleRequestDto4);
        articleService.addArticle(articleRequestDto5);

        //when
        List<Article> articleFeed = articleService.getArticleFeed(1, 3);

        //then
        assertThat(articleFeed.size()).isEqualTo(3);
    }

    @DisplayName("Article 조회")
    @Transactional
    @Test
    void getArticleByIdTest() {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("title 1 2", "description", "body", null);
        ArticleRequestDto articleRequestDto2 = new ArticleRequestDto("title 2", "description", "body", null);
        ArticleRequestDto articleRequestDto3 = new ArticleRequestDto("title 3", "description", "body", null);
        ArticleRequestDto articleRequestDto4 = new ArticleRequestDto("title4", "description", "body", null);
        ArticleRequestDto articleRequestDto5 = new ArticleRequestDto("title5", "description", "body", null);

        articleService.addArticle(articleRequestDto1);
        articleService.addArticle(articleRequestDto2);
        articleService.addArticle(articleRequestDto3);
        articleService.addArticle(articleRequestDto4);
        articleService.addArticle(articleRequestDto5);

        //when
        List<Article> articleFeed = articleService.getArticleFeed(1, 3);

        //then
        assertThat(articleFeed.size()).isEqualTo(3);

        //when
        Article article = articleService.getArticleBySlug("title-1-2");

        //then
        assertThat(article.getSlug()).isEqualTo("title-1-2");
    }

    @DisplayName("Article 업데이트")
    @Transactional
    @Test
    void updateArticleTest() {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("title test", "description", "body", null);

        String slug = articleService.addArticle(articleRequestDto1).getSlug();
        ArticleUpdateRequestDto articleUpdateRequestDto = new ArticleUpdateRequestDto();
        articleUpdateRequestDto.setTitle("title update");
        //when
        Article article = articleService.updateArticleBySlug(slug, articleUpdateRequestDto);

        //then
        assertThat(article.getSlug()).isEqualTo("title-update");
    }

    @DisplayName("Comment 추가")
    @Transactional
    @ParameterizedTest
    @CsvSource({"test, testComment", "test1, testComment2"})
    void addCommentTest(String slug, String body) {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("test", "description", "body", null);
        ArticleRequestDto articleRequestDto2 = new ArticleRequestDto("test1", "description", "body", null);
        CommentRequest commentRequest = new CommentRequest(body);

        UserDto testUser = UserDto.createMock("testUser");
        User user = userService.addUser(testUser);
        articleService.addArticle(articleRequestDto1);
        articleService.addArticle(articleRequestDto2);

        //when
        Comment comment = articleService.addComment(slug, commentRequest, user);

        //then
        Assertions.assertThat(comment).isEqualTo(commentRepository.findById(comment.getId()).get());
    }

    @DisplayName("Comment 조회")
    @Transactional
    @Test
    void getCommentByIdTest() {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("test", "description", "body", null);
        CommentRequest commentRequest1 = new CommentRequest("testComment1");
        CommentRequest commentRequest2 = new CommentRequest("testComment2");

        UserDto testUser = UserDto.createMock("testUser");
        User user = userService.addUser(testUser);
        articleService.addArticle(articleRequestDto1);

        articleService.addComment("test", commentRequest1, user);
        articleService.addComment("test", commentRequest2, user);

        //when
        List<Comment> test = articleService.getCommentByArticleSlug("test");

        //then
        Assertions.assertThat(test.size()).isEqualTo(2);
    }
}
