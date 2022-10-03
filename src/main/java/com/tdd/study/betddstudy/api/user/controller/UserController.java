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

    @GetMapping("/profiles/{username}")
    public Object getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

    //TODO 현재 토큰을 통해서 개인정보를 가져오는 기능이 없으므로, 임시로 사용자 값을 받아서 진행
    @PostMapping("/profiles/{username}/follow")
    public Object followUser(String followerName, @PathVariable String username) {
        return userService.addFollow(followerName, username);
    }
}
