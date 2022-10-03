package com.tdd.study.betddstudy.api.user.dto;

import com.tdd.study.betddstudy.api.user.entity.User;
import lombok.*;
import org.springframework.lang.Nullable;

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

    public UserDto create(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getName());
        userDto.setBio(user.getBio());
        userDto.setImage(user.getImage());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static UserDto createMock(String username) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword("password");
        userDto.setEmail(String.format("%s@test.com", username));

        return userDto;
    }
}
