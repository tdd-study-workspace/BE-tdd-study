package com.tdd.study.betddstudy.api.user.repository;

import com.tdd.study.betddstudy.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
