package com.tdd.study.betddstudy.user;

import com.tdd.study.betddstudy.api.user.dto.ProfileResponse;
import com.tdd.study.betddstudy.api.user.dto.UserDto;
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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("프로필이 있을 때 조회 테스트")
    void registerFailTest(String username, String email, String password) {
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

        //when, then
       assertThatThrownBy(() -> userService.addUser(userDto))
               .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @CsvSource({
            "username1, email1, password1, bio1, image1",
            "username2, email2, password2, bio2, image2",
            "username3, email3, password3, bio3, image3"
    })
    void findUserProfileTest(String username, String email, String password, String bio, String image) {
        //given
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setBio(bio);
        userDto.setImage(image);

        userService.addUser(userDto);

        //when
        ProfileResponse profileResponse = userService.getProfile(username);

        //then
        assertAll(
            () -> assertThat(profileResponse.getBio()).isNotEmpty(),
            () -> assertThat(profileResponse.getImage()).isNotEmpty()
        );
    }

    @DisplayName("프로필이 없어도 조회가 되는지 테스트")
    @ParameterizedTest
    @CsvSource({
            "username1, email1, password1, bio1, ",
            "username2, email2, password2, , "
    })
    void notFindUserProfileTest(String username, String email, String password, String bio, String image) {
        //given
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setBio(bio);
        userDto.setImage(image);

        userService.addUser(userDto);

        //when
        ProfileResponse profileResponse = userService.getProfile(username);

        //then
        assertThat(profileResponse.getImage()).isNullOrEmpty();
    }
}
