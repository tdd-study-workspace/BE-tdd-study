package com.tdd.study.betddstudy.user;

import com.tdd.study.betddstudy.api.user.dto.ProfileResponse;
import com.tdd.study.betddstudy.api.user.dto.UserDto;
import com.tdd.study.betddstudy.api.user.entity.Follow;
import com.tdd.study.betddstudy.api.user.entity.User;
import com.tdd.study.betddstudy.api.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Transactional
    @ParameterizedTest
    @CsvSource({
            "username1, email1, password1",
            "username2, email2, password2",
            "username3, email3, password3"
    })
    @DisplayName("회원가입 성공 테스트")
    void registerSuccessTest(String username, String email, String password) {
        //given
        User user = User.builder()
                .name(username)
                .email(email)
                .password(password)
                .build();

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setEmail(email);

        //when
        User saveUser = userService.addUser(userDto);

        //then
        assertThat(saveUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Transactional
    @ParameterizedTest
    @CsvSource({
            "username1, email1, ",
            "username2, , password",
            " , email3, password3"
    })
    @DisplayName("회원가입 실패 테스트")
    void registerFailTest(String username, String email, String password) {
        //given
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setEmail(email);

        //when

        //when, then
       assertThatThrownBy(() -> userService.addUser(userDto))
               .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("사용자 프로필 조회 테스트")
    void findUserProfileTest() {
        //given
        UserDto userDto = UserDto.createMock("profile-test-name-01");

        userService.addUser(userDto);

        //when
        ProfileResponse profileResponse = userService.getProfile(userDto.getUsername());

        //then
        assertAll(
            () -> assertThat(profileResponse.getBio()).isNotEmpty(),
            () -> assertThat(profileResponse.getImage()).isNotEmpty()
        );
    }

    @Test
    @DisplayName("프로필이 없어도 조회가 되는지 테스트")
    void notFindUserProfileTest() {
        //given
        UserDto userDto = UserDto.createMock("test01");

        userService.addUser(userDto);

        //when
        ProfileResponse profileResponse = userService.getProfile(userDto.getUsername());

        //then
        assertThat(profileResponse.getImage()).isNullOrEmpty();
    }

    @Test
    @DisplayName("팔로우 성공 테스트")
    void followSuccessTest() {
        //given
        UserDto userDto1 = UserDto.createMock("username01");
        UserDto userDto2 = UserDto.createMock("username02");

        User user1 = userService.addUser(userDto1);
        User user2 = userService.addUser(userDto2);

        //when
        Follow result = userService.addFollow(user1.getName(), user2.getName());

        //then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("팔로우 실패 테스트")
    void followFailTest() {
        //given
        UserDto userDto1 = UserDto.createMock("username01");

        User user1 = userService.addUser(userDto1);

        //when

        //then
        assertThatThrownBy(() -> userService.addFollow(user1.getName(), "unknown"))
                .isInstanceOf(Exception.class);

    }

    @Test
    @DisplayName("언팔로우 성공 테스트")
    void unfollowSuccessTest() {
        //given
        UserDto userDto1 = UserDto.createMock("username01");
        UserDto userDto2 = UserDto.createMock("username02");

        User user1 = userService.addUser(userDto1);
        User user2 = userService.addUser(userDto2);

        userService.addFollow(user1.getName(), user2.getName());

        //when
        boolean unfollow = userService.deleteFollow(userDto1.getUsername(), userDto2.getUsername());

        //then
        assertThat(unfollow).isTrue();
    }

    @Test
    @DisplayName("언팔로우 실패 테스트")
    void unfollowFailTest() {
        UserDto userDto1 = UserDto.createMock("username01");
        UserDto userDto2 = UserDto.createMock("username02");

        User user1 = userService.addUser(userDto1);
        User user2 = userService.addUser(userDto2);

//        userService.addFollow(user1.getName(), user2.getName());

        //when
        //then
        assertThatThrownBy(() -> userService.deleteFollow(userDto1.getUsername(), userDto2.getUsername()))
                .isInstanceOf(Exception.class);
    }
}
