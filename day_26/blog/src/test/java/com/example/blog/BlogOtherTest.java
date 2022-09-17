package com.example.blog;

import com.example.blog.dto.BlogInfo;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BlogOtherTest {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void get_all_blog_info() {
        List<BlogInfo> blogInfos = blogRepository.getAllBlogInfo();
        blogInfos.forEach(System.out::println);
    }

    @Test
    @Rollback(value = false)
    void delete_comment_by_id() {
        commentRepository.deleteById(2);
    }
}
