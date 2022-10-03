package com.tdd.study.betddstudy.api.profile.service;

import com.tdd.study.betddstudy.api.profile.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    /**
     * 내가 {username} 을 팔로잉 하고 있는 지 확인
     * @param username
     * @return
     */
    public Boolean getFollowing(String username) {
        return true;
    }
}
