package com.example.blog;

import com.example.blog.entity.*;
import com.example.blog.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SpringBootTest
class InitDataTest {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IdentityCardRepository identityCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Random rd;

    @Test
    void save_user() {
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .identityCard(new IdentityCard())
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void save_image() {
        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        for (int i = 0; i < 50; i++) {
            // Random ra 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            // Tạo image
            Image image = Image.builder()
                    .url(faker.company().logo())
                    .user(rdUser)
                    .build();

            imageRepository.save(image);
        }
    }

    @Test
    void save_avatar_user() {
        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            // Lấy danh sách image của user đó
            List<Image> images = imageRepository.getByUser_Id(user.getId());

            // Random ra 1 image
            String link = images.get(rd.nextInt(images.size())).getUrl();

            // Set avatar cho user
            user.setAvatar(link);

            // Lưu lại
            userRepository.save(user);
        });
    }

    @Test
    void save_category() {
        for (int i = 0; i < 5; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();

            categoryRepository.save(category);
        }
    }

    @Test
    void save_blog() {
        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        // Lấy danh sách category
        List<Category> categories = categoryRepository.findAll();

        for (int i = 0; i < 30; i++) {
            // Random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            // Random ra 1 image
            List<Image> images = imageRepository.getByUser_Id(rdUser.getId());
            String link = images.get(rd.nextInt(images.size())).getUrl();

            // Random 1 set category
            Set<Category> rdCategories = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categories.get(rd.nextInt(categories.size()));
                rdCategories.add(rdCategory);
            }

            Blog blog = Blog.builder()
                    .title(faker.lorem().sentence(10))
                    .description(faker.lorem().sentence(30))
                    .content(faker.lorem().sentence(100))
                    .thumbnail(link)
                    .status(rd.nextInt(2) == 0)
                    .user(rdUser)
                    .categories(rdCategories)
                    .build();

            blogRepository.save(blog);
        }
    }

    @Test
    void save_comment() {
        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        // Lấy danh sách blog
        List<Blog> blogs = blogRepository.findAll();

        for (int i = 0; i < 100; i++) {
            // Random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            // Random 1 blog
            Blog rdBlog = blogs.get(rd.nextInt(blogs.size()));

            Comment comment = Comment.builder()
                    .content(faker.lorem().sentence(20))
                    .blog(rdBlog)
                    .user(rdUser)
                    .build();

            commentRepository.save(comment);
        }
    }
}
