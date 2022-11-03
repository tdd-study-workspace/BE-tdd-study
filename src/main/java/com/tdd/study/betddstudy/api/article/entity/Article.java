package com.tdd.study.betddstudy.api.article.entity;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
    name = "article_seq_gen",
    sequenceName = "article_seq",
    initialValue = 0,
    allocationSize = 10
)
@EqualsAndHashCode
@Builder
public class Article extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_seq_gen")
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
