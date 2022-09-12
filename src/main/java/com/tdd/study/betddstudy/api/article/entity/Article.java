package com.tdd.study.betddstudy.api.article.entity;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Article extends BaseEntity {
    @Id
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User author;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleTag> tagList;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Favorite> favoriteList;
}
