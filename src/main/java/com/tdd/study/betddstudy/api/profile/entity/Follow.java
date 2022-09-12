package com.tdd.study.betddstudy.api.profile.entity;

import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow extends BaseEntity {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "followee")
    private User followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "follower")
    private User follower;
}
