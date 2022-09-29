package com.example.blog.controller;

import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String getCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/category/index";
    }
}
