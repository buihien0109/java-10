package com.example.userbackend.repository;

import com.example.userbackend.database.FakeDB;
import com.example.userbackend.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    // Lưu user
    public User save(User user) {
        FakeDB.users.add(user);
        return user;
    }

    // Lấy ds user
    public List<User> getAll() {
        return FakeDB.users;
    }

    // Tìm kiếm user theo tên
    public List<User> findUsersByNameContainingIgnoreCase(String name) {
        return FakeDB.users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Lấy thông tin user theo id
    public Optional<User> findById(int id) {
        return FakeDB.users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    // Lấy thông tin user theo email
    public Optional<User> findByEmail(String email) {
        return FakeDB.users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    // Xóa user
    public void delete(User user) {
        FakeDB.users.remove(user);
    }

    public void delete(int id) {
        FakeDB.users.removeIf(user -> user.getId() == id);
    }
}
