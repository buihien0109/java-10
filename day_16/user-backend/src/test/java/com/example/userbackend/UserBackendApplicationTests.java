package com.example.userbackend;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.service.FileService;
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

    @Autowired
    private FileService fileService;


    @Test
    void get_all_user() {
        List<UserDto> userDtos = userService.getAll();

        userDtos.forEach(System.out::println);

        Assertions.assertThat(userDtos).isNotNull();
        Assertions.assertThat(userDtos.size()).isEqualTo(25);
    }

    @Test
    void get_extension_file_test() {
        String rs = fileService.getExtensionFile("avatar.png");
        String rs1 = fileService.getExtensionFile("avatar.jpeg");
        System.out.println(rs);
        System.out.println(rs1);
    }
}
