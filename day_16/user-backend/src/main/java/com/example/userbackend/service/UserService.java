package com.example.userbackend.service;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.model.User;
import com.example.userbackend.repository.UserRepository;
import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdateAvatarRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Faker faker;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final FileService fileService;

    public UserService(UserRepository userRepository, Faker faker, ModelMapper modelMapper, EmailService emailService, FileService fileService) {
        this.userRepository = userRepository;
        this.faker = faker;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.fileService = fileService;

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

    // Tạo user
    public UserDto createUser(CreateUserRequest request) {
        // Kiểm tra email, xem có trùng với user nào đó hay không
        // Nếu có -> Báo lỗi
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email = " + request + " đã tồn tại");
        }

        // Tạo user với các thông tin tử request
        Random rd = new Random();
        User user = User.builder()
                .id(rd.nextInt(1000))
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();

        userRepository.save(user);

        // Trả về userDto sau khi tạo
        return modelMapper.map(user, UserDto.class);
    }

    public void forgotPassword(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        });

        // Generate password
        Random rd = new Random();
        String newPassword = String.valueOf(rd.nextInt(1000));

        user.setPassword(newPassword);

        // Send email
        emailService.send("hien@techmaster.vn", "Quên mật khẩu", "Mật khẩu mới : " + newPassword);
    }

    public void updatePassword(int id, UpdatePasswordRequest request) {
        // Kiểm tra id của user xem có tồn tại hay không -> Lỗi NotFound

        // Kiểm tra oldPass có chính xác hay không
        // Nếu oldPass không chính xác -> Lỗi BadRequest

        // Kiểm tra oldPass có giống newPass hay không
        // Nếu newPass giống oldPass -> Lỗi BadRequest

        // Set lại newPass cho user
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
        if(userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        return fileService.uploadFile(id, file);
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        if(userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        return fileService.readFile(id, fileId);
    }

    // Lấy danh sách file
    public List<String> getFiles(int id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        return fileService.getFiles(id);
    }

    // Xóa file
    public void deleteFile(int id, String fileId) {
        if(userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        fileService.deleteFile(id, fileId);
    }

    // Cập nhật avatar
    public void updateAvatar(int id, UpdateAvatarRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        });

        user.setAvatar(request.getAvatar());
    }
}
