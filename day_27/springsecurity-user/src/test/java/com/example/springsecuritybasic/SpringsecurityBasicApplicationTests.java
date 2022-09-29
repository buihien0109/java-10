package com.example.springsecuritybasic;

import com.example.springsecuritybasic.entity.User;
import com.example.springsecuritybasic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class SpringsecurityBasicApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_users() {
        User user1 = User.builder()
                .name("Bui Hien")
                .email("hien@gmail.com")
                .password(passwordEncoder.encode("111"))
                .roles(List.of("USER", "EDITOR", "ADMIN"))
                .build();

        User user2 = User.builder()
                .name("Pham Man")
                .email("man@gmail.com")
                .password(passwordEncoder.encode("111"))
                .roles(List.of("USER", "EDITOR"))
                .build();

        User user3 = User.builder()
                .name("Duc Thinh")
                .email("thinh@gmail.com")
                .password(passwordEncoder.encode("111"))
                .roles(List.of("USER"))
                .build();

        userRepository.saveAll(List.of(user1, user2, user3));
    }

}
