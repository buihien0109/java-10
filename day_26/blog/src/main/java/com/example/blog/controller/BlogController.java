package com.example.blog.controller;

import com.example.blog.request.CreateBlogRequest;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    // Danh sách tất cả bài viết
    @GetMapping("/admin/blogs")
    public String getBlogPage(Model model) {
        // Todo : Bổ sung phân trang
        model.addAttribute("blogs", blogService.getAllBlogDto());

        return "admin/blog/blog-index";
    }

    // Danh sách bài viết của tôi
    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogPage(Model model) {
        // Todo : Về sau userId chính là id của user đang login
        Integer userId = 1;

        model.addAttribute("blogs", blogService.getAllBlogDtoByUserId(userId));

        return "admin/blog/blog-yourself";
    }

    // Tạo bài viết
    @GetMapping("/admin/blogs/create")
    public String getBlogCreatePage(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        return "admin/blog/blog-create";
    }

    // Chi tiết bài viết
    @GetMapping("/admin/blogs/{id}/detail")
    public String getBlogDetailPage(@PathVariable String id) {
        return "admin/blog/blog-detail";
    }

    @PostMapping("/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request) {
        // Todo : Về sau userId chính là id của user đang login
        Integer userId = 1;

        return ResponseEntity.ok(blogService.createBlog(userId, request));
    }
}
