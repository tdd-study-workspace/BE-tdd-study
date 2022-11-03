package com.tdd.study.betddstudy.api.user.repository;

import com.tdd.study.betddstudy.api.user.entity.Follow;
import com.tdd.study.betddstudy.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowee(User follower, User followee);
}
