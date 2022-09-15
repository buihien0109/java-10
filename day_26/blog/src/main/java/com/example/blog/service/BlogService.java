package com.example.blog.service;

import com.example.blog.dto.BlogDto;
import com.example.blog.entity.Blog;
import com.example.blog.repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Lấy tất cả bài viết ở dạng BlogDto
    public List<BlogDto> getAllBlogDto() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .sorted((a,b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    // Lấy tất cả bài viết của user ở dạng BlogDto
    public List<BlogDto> getAllBlogDtoByUserId(Integer id) {
        List<Blog> blogs = blogRepository.getByUser_IdOrderByCreatedAtDesc(id);

        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }
}
