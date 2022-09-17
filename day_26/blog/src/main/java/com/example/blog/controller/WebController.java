package com.example.blog.controller;

import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogInfo());
        return "web/index";
    }

    @GetMapping("/blogs/{id}")
    public String getDetailPage(@PathVariable Integer id, Model model) {
        model.addAttribute("blogs", blogService.getBlogInfoById(id));
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