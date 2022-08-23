package com.example.userbackend.controller;

import com.example.userbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String getUserDetailPage(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "detail";
    }
}
