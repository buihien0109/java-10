package com.example.userbackend.controller;

import com.example.userbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
