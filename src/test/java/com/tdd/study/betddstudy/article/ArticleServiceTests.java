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
        Assertions.assertThat(article.getId()).isEqualTo(0L);
    }
}
