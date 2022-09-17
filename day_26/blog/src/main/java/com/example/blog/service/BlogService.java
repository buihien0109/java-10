package com.example.blog.service;

import com.example.blog.dto.BlogDto;
import com.example.blog.dto.BlogInfo;
import com.example.blog.entity.Blog;
import com.example.blog.entity.Category;
import com.example.blog.entity.User;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.request.CreateBlogRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


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

    @Transactional
    public BlogDto createBlog(Integer userId, CreateBlogRequest request) {
        // Lấy ra user
        User user = userRepository.getById(userId);

        // Lấy ra ds category dựa vào mảng id từ request
        Set<Category> categories = categoryRepository.getByIdIn(request.getCategoryIds());

        // Tạo blog
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .status(request.getStatus())
                .user(user)
                .categories(categories)
                .build();

        blogRepository.save(blog);

        return modelMapper.map(blog, BlogDto.class);
    }

    public List<BlogInfo> getAllBlogInfo() {
        return blogRepository.getAllBlogInfo();
    }

    public Object getBlogInfoById(Integer id) {
        List<BlogInfo> blogInfos = blogRepository.getAllBlogInfo();

        return null;
    }
}
