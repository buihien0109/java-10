package com.example.todolistbackend;

import com.example.todolistbackend.entity.Todo;
import com.example.todolistbackend.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TodolistBackendApplicationTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void save_todo() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Đi chơi"));
        todos.add(new Todo("Làm bài tập Java", true));
        todos.add(new Todo("Đá bóng"));
        todos.add(new Todo("Chơi game"));

        todoRepository.saveAll(todos);
    }

}
