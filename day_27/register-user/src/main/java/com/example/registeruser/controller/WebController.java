package com.example.registeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    // Public với mọi user
    @GetMapping("/")
    public String getHome() {
        return "Home page";
    }

    // Cần có quyền USER thì mới vào được
    @GetMapping("/profile")
    public String getProfilePage() {
        return "Profile page";
    }
}
