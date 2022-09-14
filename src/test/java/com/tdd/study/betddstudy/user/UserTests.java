package com.tdd.study.betddstudy.user;

import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("최근 사용자 조회하는 기능 테스트")
    void getCurrentUserTest() {
        //given
        User user = User.builder()
                .id(0L)
                .name("test-user")
                .password("test-password")
                .build();

        userRepository.save(user);

        //when
        User currentUser = userRepository.findById(0L).orElseThrow();


        //then

    }
}
