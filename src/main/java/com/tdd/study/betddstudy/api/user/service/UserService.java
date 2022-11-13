package com.tdd.study.betddstudy.api.user.service;

import com.tdd.study.betddstudy.api.user.dto.ProfileResponse;
import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.entity.Follow;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.repository.FollowRepository;
import com.tdd.study.betddstudy.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
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
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setUsername(user.getName());
        profileResponse.setBio(user.getBio());
        profileResponse.setImage(user.getImage());
        profileResponse.setFollowing(this.getFollowing(username));

        return profileResponse;
    }

    public Boolean getFollowing(String username) {
        return true;
    }

    //TODO 토큰 적용되면 리팩토링 필요 -> followerName이 제거되어야함
    @Transactional
    public Follow addFollow(String followerName, String username) {

        User follower = userRepository.findByName(followerName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User followee = userRepository.findByName(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Follow follow = Follow.builder()
                .follower(follower)
                .followee(followee)
                .build();

        return followRepository.save(follow);
    }

    //TOOD 토큰 적용되면 리팩토링 필요 -> followerName이 제거되어야함
   @Transactional
    public boolean deleteFollow(String followerName, String followeeName) {
        User follower = userRepository.findByName(followerName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User followee = userRepository.findByName(followeeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Follow follow = followRepository.findByFollowerAndFollowee(follower, followee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        followRepository.delete(follow);

        return true;
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
