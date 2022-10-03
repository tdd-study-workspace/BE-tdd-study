package com.tdd.study.betddstudy.api.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProfileResponse {
    private String username;
    private String bio;
    private String image;
    private Boolean following;
}
