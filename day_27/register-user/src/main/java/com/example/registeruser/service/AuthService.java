package com.example.registeruser.service;

import com.example.registeruser.request.LoginRequest;
import com.example.registeruser.request.RegisterRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {
    public String login(LoginRequest request, HttpSession session) {
        return null;
    }

    public String logout(HttpSession session) {
        return null;
    }

    public String register(RegisterRequest request) {
        return null;
    }

    public String confirm(String token) {
        return null;
    }
}
