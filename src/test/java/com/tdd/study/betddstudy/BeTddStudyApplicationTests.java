package com.tdd.study.betddstudy;

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
class BeTddStudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("TDD 첫 테스트 입니다.")
    void getFirstTest() {
        //given
        String StringArg = "TDD";

        //when
        String actual = "TDD";
        String expected = "TDD";

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("TDD 두번째 테스트 입니다.")
    void getSecondTest() {
        //given
        String username = "윤혁준";
        String email = "gurwns1540@gmail.com";

        User user = User.builder().username(username).email(email).build();

        //when
        User saveUser = userRepository.save(user);

        //then
        assertThat(saveUser.getUsername()).isEqualTo(user.getUsername());

    }
}
