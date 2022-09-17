package com.example.blog.repository;

import com.example.blog.dto.BlogInfo;
import com.example.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> getByUser_IdOrderByCreatedAtDesc(Integer id);

    @Query(nativeQuery = true, name = "getAllBlogInfo")
    List<BlogInfo> getAllBlogInfo();
}