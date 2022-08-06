package com.example.student;

import com.example.student.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

//    @Bean
//    public StudentService studentService() {
//        return new StudentService();
//    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
