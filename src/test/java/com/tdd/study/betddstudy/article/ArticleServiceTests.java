package com.tdd.study.betddstudy.article;

import com.tdd.study.betddstudy.api.article.dto.ArticleRequestDto;
import com.tdd.study.betddstudy.api.article.entity.Article;
import com.tdd.study.betddstudy.api.article.entity.Article.ArticleBuilder;
import com.tdd.study.betddstudy.api.article.service.ArticleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

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
        assertThat(article.getId()).isEqualTo(0L);
    }

    @DisplayName("Article List 조회")
    @Transactional
    @Test
    void getArticleListTest() {
        //given
        ArticleRequestDto articleRequestDto1 = new ArticleRequestDto("title", "description", "body", null);
        ArticleRequestDto articleRequestDto2 = new ArticleRequestDto("title", "description", "body", null);
        ArticleRequestDto articleRequestDto3 = new ArticleRequestDto("title", "description", "body", null);

        articleService.addArticle(articleRequestDto1);
        articleService.addArticle(articleRequestDto2);
        articleService.addArticle(articleRequestDto3);

        //when
        List<Article> article = articleService.getArticle();

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
}
