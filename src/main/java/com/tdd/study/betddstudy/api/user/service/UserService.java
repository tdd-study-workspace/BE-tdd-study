package com.tdd.study.betddstudy.api.user.service;

import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.repository.UserRepository;
import com.tdd.study.betddstudy.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email in loadUserByUsername = " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}
