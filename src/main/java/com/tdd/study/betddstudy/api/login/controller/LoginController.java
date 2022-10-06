package com.tdd.study.betddstudy.api.login.controller;

import com.tdd.study.betddstudy.api.login.dto.LoginRequest;
import com.tdd.study.betddstudy.api.login.service.LoginService;
import com.tdd.study.betddstudy.global.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.login(loginRequest));
    }

}
