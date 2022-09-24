package com.tdd.study.betddstudy.api.user.service;

import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .name(userDto.getUsername())
                .build();

        if(!StringUtils.hasText(userDto.getUsername())) {
            throw new IllegalArgumentException("not find username");
        } else if(!StringUtils.hasText(userDto.getPassword())) {
            throw new IllegalArgumentException("not found password");
        } else if(!StringUtils.hasText(userDto.getEmail())) {
            throw new IllegalArgumentException("not found email");
        }

        return userRepository.save(user);
    }
}
