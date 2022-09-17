package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHomePage(Model model) {
        return "web/index";
    }

    @GetMapping("/blogs/{id}")
    public String getDetailPage(@PathVariable String id, Model model) {
        return "web/detail";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "web/about";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "web/contact";
    }
}