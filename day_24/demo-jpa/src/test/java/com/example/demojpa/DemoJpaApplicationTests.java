package com.example.demojpa;

import com.example.demojpa.dto.UserDto;
import com.example.demojpa.dto.UserInfo;
import com.example.demojpa.entity.User;
import com.example.demojpa.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class DemoJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;

    @Test
    void save_user_to_db_test() {
        User user = User.builder()
                .name("Nguyễn Văn A")
                .email("a@gmail.com")
                .age(20)
                .birth(LocalDate.now().minusYears(20))
                .build();

        User user1 = User.builder()
                .name("Trần Văn B")
                .email("b@gmail.com")
                .age(40)
                .birth(LocalDate.now().minusYears(40))
                .build();

        User user2 = User.builder()
                .name("Ngô Thị C")
                .email("c@gmail.com")
                .age(30)
                .birth(LocalDate.now().minusYears(30))
                .build();

        // Lưu từng cái
//        userRepository.save(user);
//        userRepository.save(user1);
//        userRepository.save(user2);

        userRepository.saveAll(List.of(user, user1, user2));
    }

    @Test
    void save_fake_user() {
        for (int i = 0; i < 50; i++) {
            int age = faker.number().numberBetween(20, 40);
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .age(age)
                    .birth(LocalDate.now().minusYears(age))
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void find_by_name_test() {
        List<User> users = userRepository.findByNameContainingIgnoreCase("văn");
        users.forEach(System.out::println);
    }

    @Test
    void find_by_age_test() {
        List<User> users = userRepository.getByAgeGreaterThanOrderByAgeDesc(25);
        users.forEach(System.out::println);
    }

    @Test
    void find_by_id_jpql() {
        User user = userRepository.test(1);
        System.out.println(user);
    }

    @Test
    void find_by_id_native_query() {
        User user = userRepository.test2(1);
        System.out.println(user);
    }

    @Test
    void find_user_dto_by_id_test() {
        UserDto user = userRepository.getUserDtoById(1);
        System.out.println(user);
    }

    @Test
    void find_by_id_user_dto_using_projection() {
        UserInfo user = userRepository.getUserInfoById(1);
        System.out.println(user.getId() + " - " + user.getName() + " - " + user.getEmail());
    }

    @Test
    void find_by_id_user_dto_using_native_query() {
        UserDto user = userRepository.getUserByIdUsingNativeQuery(1);
        System.out.println(user);
    }

    @Test
    void sort_user_by_name_desc() {
        List<User> users = userRepository.findAll(Sort.by("name", "age").descending());
        users.forEach(System.out::println);
    }

    @Test
    void pagination_user_test() {
        Page<User> page = userRepository.findAll(PageRequest.of(0, 5, Sort.by("id").descending()));
        page.getContent().forEach(System.out::println);
    }

    @Test
    void update_user_by_entity() {
        User user = userRepository.getUserById(1);

        System.out.println(user);

        user.setName("Other name 1");

        userRepository.save(user);
    }

    @Test
    void update_user_by_query() {
        userRepository.updateUser(2, "Bùi Hiên");
    }
}
