package com.tdd.study.betddstudy.api.profile.repository;

import com.tdd.study.betddstudy.api.profile.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
}
