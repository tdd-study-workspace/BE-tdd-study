package com.tdd.study.betddstudy.api.user.entity;

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
@SequenceGenerator(name = "follow_id_seq_gen", sequenceName = "follow_id_seq", allocationSize = 1)
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_id_seq_gen")
    private Long id;

    /**
     * 내가 따르고싶은 사람
     * 내가 보고싶어하는 사람
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "followee")
    private User followee;

    /**
     * 나를 보고싶어하는 사람들
     * 나를 따르는 사람들
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "follower")
    private User follower;
}
