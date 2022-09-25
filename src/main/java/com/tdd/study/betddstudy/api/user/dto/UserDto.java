package com.tdd.study.betddstudy.api.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {
    private String email;
    private String password;
    private String token;
    private String username;
    private String bio;
    private String image;
}
