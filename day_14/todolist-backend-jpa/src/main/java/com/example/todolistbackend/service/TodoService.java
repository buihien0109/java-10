package com.example.todolistbackend.service;

import com.example.todolistbackend.exception.BadRequestException;
import com.example.todolistbackend.exception.NotFoundException;
import com.example.todolistbackend.entity.Todo;
import com.example.todolistbackend.repository.TodoRepository;
import com.example.todolistbackend.request.CreateTodoRequest;
import com.example.todolistbackend.request.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Lấy ds tất cả cv
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // Lấy ds tất cả cv theo trạng thái
    public List<Todo> getTodos(Boolean status) {
        return todoRepository.findByStatus(status);
    }

    // Tạo cv
    public Todo createTodo(CreateTodoRequest request) {
        if (request.getTitle().trim().equals("")) {
            throw new BadRequestException("Tiêu đề không được để trống");
        }

        return todoRepository.save(new Todo(request.getTitle()));
    }

    // Cập nhật cv
    public Todo updateTodo(Integer id, UpdateTodoRequest request) {
        if (request.getTitle().trim().equals("")) {
            throw new BadRequestException("Tiêu đề không được để trống");
        }

        Todo todo = todoRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy cv có id = " + id);
        });

        todo.setTitle(request.getTitle());
        todo.setStatus(request.getStatus());

        todoRepository.save(todo);

        return todo;
    }

    // Xóa cv
    public void deleteTodo(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy cv có id = " + id);
        });

        todoRepository.delete(todo);
    }
}
