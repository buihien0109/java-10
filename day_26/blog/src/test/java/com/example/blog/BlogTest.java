package com.example.blog;

import com.example.blog.dto.BlogDto;
import com.example.blog.dto.BlogInfo;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import com.example.blog.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BlogTest {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;


    @Test
    void get_all_blog_dto_test() {
        List<BlogDto> blogDtos = blogService.getAllBlogDto();

        blogDtos.forEach(System.out::println);
    }

    @Test
    void generate_category_string_test() {
        List<BlogDto> blogDtos = blogService.getAllBlogDto();
        blogDtos.forEach(blogDto -> System.out.println(Utils.generateCategoryString(blogDto.getCategories())));
    }

    @Test
    void get_all_blog_info() {
        List<BlogInfo> blogInfos = blogRepository.getAllBlogInfo();
        blogInfos.forEach(System.out::println);
    }
}
