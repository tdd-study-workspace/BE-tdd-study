package com.tdd.study.betddstudy.api.login.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {

    private String email;
    private String password;
}
