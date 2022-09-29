package com.tdd.study.betddstudy.api.user.controller;

import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public Object registration(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("/profile/{username}")
    public Object getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }
}
