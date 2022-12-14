package com.tdd.study.betddstudy.api.article.entity;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@Builder
@EqualsAndHashCode(callSuper = false)
public class Article extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_seq_gen")
    private Long id;
    @Column(unique = true)
    private String slug;
    @Column(unique = true)
    private String title;
    private String description;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User author;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleTag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Favorite> favoriteList = new ArrayList<>();

    public void update(Article update) {
        Optional.ofNullable(update.getSlug()).ifPresent(none -> this.slug = update.getSlug());
        Optional.ofNullable(update.getTitle()).ifPresent(none -> this.title = update.getTitle());
        Optional.ofNullable(update.getBody()).ifPresent(none -> this.body = update.getBody());
        Optional.ofNullable(update.getDescription()).ifPresent(none -> this.description = update.getDescription());
        Optional.ofNullable(update.getFavoriteList()).ifPresent(none -> this.favoriteList = update.getFavoriteList());
    }
}
