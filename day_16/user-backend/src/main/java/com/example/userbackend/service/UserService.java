package com.example.userbackend.service;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.model.User;
import com.example.userbackend.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Faker faker;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, Faker faker, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.faker = faker;
        this.modelMapper = modelMapper;

        initData();
    }

    public void initData() {
        Random rd = new Random();
        List<String> cities = new ArrayList<>(List.of("Thành phố Hà Nội", "Thành phố Đà Nẵng", "Thành phố Hồ Chí Minh"));

        for (int i = 1; i <= 25 ; i++) {
            int rdIndex = rd.nextInt(cities.size());
            String rdCity = cities.get(rdIndex);

            User user = User.builder()
                    .id(i)
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .password("111")
                    .avatar(faker.company().logo())
                    .address(rdCity)
                    .build();

            userRepository.save(user);
        }
    }

    // Lấy danh sách user -> Trả về Dto
    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Tìm kiếm user
    public List<UserDto> searchUser(String name) {
        List<User> users = userRepository.findUsersByNameContainingIgnoreCase(name);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Xóa user
    public void deleleUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
           throw new NotFoundException("Không tìm thấy user có id = " + id);
        });

        userRepository.delete(user.getId());
    }
}
