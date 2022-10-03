package com.tdd.study.betddstudy.api.user.service;

import com.tdd.study.betddstudy.api.profile.service.FollowService;
import com.tdd.study.betddstudy.api.user.dto.ProfileResponse;
import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FollowService followService;
    private final UserRepository userRepository;

    public User addUser(UserDto userDto) {

        User user = User.create(userDto);

        if(!StringUtils.hasText(userDto.getUsername())) {
            throw new IllegalArgumentException("not find username");
        } else if(!StringUtils.hasText(userDto.getPassword())) {
            throw new IllegalArgumentException("not found password");
        } else if(!StringUtils.hasText(userDto.getEmail())) {
            throw new IllegalArgumentException("not found email");
        }

        return userRepository.save(user);
    }

    public ProfileResponse getProfile(String username) {
        User user = userRepository.findByName(username).orElseThrow();
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setUsername(user.getName());
        profileResponse.setBio(user.getBio());
        profileResponse.setImage(user.getImage());
        profileResponse.setFollowing(this.followService.getFollowing(username));

        return profileResponse;
    }
}
