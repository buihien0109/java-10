package com.example.registeruser.controller;

import com.example.registeruser.request.LoginRequest;
import com.example.registeruser.request.RegisterRequest;
import com.example.registeruser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpSession session) {
        return authService.login(request, session);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return authService.logout(session);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // http://localhost:8080/api/auth/confirm?token=hddkdjj_8hdi92h
    @GetMapping("/confirm")
    public String confirm(@RequestParam String token) {
        return authService.confirm(token);
    }
}
