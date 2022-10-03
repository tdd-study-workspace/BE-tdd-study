package com.tdd.study.betddstudy.global.security.service;

import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.repository.UserRepository;
import com.tdd.study.betddstudy.global.security.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).get();
        return UserPrincipal.create(user);
    }
}
