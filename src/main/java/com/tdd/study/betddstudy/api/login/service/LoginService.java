package com.tdd.study.betddstudy.api.login.service;

import com.tdd.study.betddstudy.api.login.dto.LoginRequest;
import com.tdd.study.betddstudy.global.security.JwtTokenProvider;
import com.tdd.study.betddstudy.global.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public TokenDto login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            TokenDto tokenDto = new TokenDto(
                    jwtTokenProvider.generateToken(authentication)
            );

            return tokenDto;
        } catch (AuthenticationException e) {
            throw new RuntimeException();
        }
    }
}
