package com.example.userbackend;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserBackendApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void get_all_user() {
        List<UserDto> userDtos = userService.getAll();

        userDtos.forEach(System.out::println);

        Assertions.assertThat(userDtos).isNotNull();
        Assertions.assertThat(userDtos.size()).isEqualTo(25);
    }

}
