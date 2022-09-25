package com.tdd.study.betddstudy.api.user.controller;

import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public Object registration(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
}
