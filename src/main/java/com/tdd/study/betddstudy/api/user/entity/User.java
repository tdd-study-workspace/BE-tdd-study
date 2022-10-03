package com.tdd.study.betddstudy.api.user.entity;

import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "user_seq_gen",
        sequenceName = "user_seq",
        initialValue = 0,
        allocationSize = 10
)
@Table(name = "tdd_user")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    Long id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private String image;

    public static User create(UserDto userDto) {
        return User.builder()
                .name(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .bio(userDto.getBio())
                .image(userDto.getImage())
                .build();
    }
}