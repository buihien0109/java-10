package com.example.student;

import com.example.student.dto.StudentDto;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void get_all_user_test() {
        List<StudentDto> students = studentService.getAll();

        // In thông tin student
        students.forEach(System.out::println);

        // Định nghĩa 1 số kết quả mong muốn
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students.size()).isEqualTo(4);
        Assertions.assertThat(students.get(0).getName()).isEqualTo("Nguyễn Văn A");
    }
}
