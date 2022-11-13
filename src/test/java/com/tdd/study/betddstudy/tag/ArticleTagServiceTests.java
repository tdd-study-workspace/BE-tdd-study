package com.tdd.study.betddstudy.tag;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import com.tdd.study.betddstudy.api.tag.service.ArticleTagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
class ArticleTagServiceTests {

    @Autowired
    private ArticleTagService articleTagService;

    @Test
    void successGetTags() {
        //given
        ArticleTag articleTag = ArticleTag.builder().build();


        //when
        List<ArticleTag> list = articleTagService.getTags();


        //then

    }
}
