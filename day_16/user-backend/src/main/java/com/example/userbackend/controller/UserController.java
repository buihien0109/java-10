package com.example.userbackend.controller;

import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdateAvatarRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.example.userbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Lấy danh sách user
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

//    @GetMapping(value = "/users", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<UserDto> getUsersOther() {
//        return userService.getAll();
//    }

    // Tìm kiếm user theo tên
    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam String name) {
        return ResponseEntity.ok(userService.searchUser(name));
    }

    // Xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleleUser(id);
        return ResponseEntity.noContent().build();
    }

    // Tạo user
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    // Quên mật khẩu
    @GetMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> forgotPassword(@PathVariable int id) {
        userService.forgotPassword(id);
        return ResponseEntity.noContent().build();
    }

    // Đổi mật khẩu
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
        return ResponseEntity.noContent().build();
    }

    // Upload file
    @PostMapping("/users/{id}/files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file, @PathVariable int id) {
        String filePath =  userService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    // Xem file
    @GetMapping("/users/{id}/files/{fileId}") // produces = {MediaType.IMAGE_JPEG_VALUE}
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable String fileId) {
        byte[] bytes = userService.readFile(id, fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    // Lấy ds file
    @GetMapping("/users/{id}/files")
    public ResponseEntity<?> getFiles(@PathVariable int id) {
        List<String> files = userService.getFiles(id);
        return ResponseEntity.ok(files);
    }

    // Xóa file
    @DeleteMapping("/users/{id}/files/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable int id, @PathVariable String fileId) {
        userService.deleteFile(id, fileId);
        return ResponseEntity.noContent().build();
    }

    // Thay đổi avatar
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @RequestBody UpdateAvatarRequest request) {
        userService.updateAvatar(id, request);
        return ResponseEntity.noContent().build();
    }
}
