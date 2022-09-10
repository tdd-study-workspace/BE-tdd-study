package com.tdd.study.betddstudy.api.user.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@SequenceGenerator(
        name = "user_seq_gen",
        sequenceName = "user_seq",
        allocationSize = 1
)
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    private Long id;
    private String username;
    private String password;
    private String email;
    private String bio;
    private String image;
}
