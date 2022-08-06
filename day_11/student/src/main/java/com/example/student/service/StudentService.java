package com.example.student.service;

import com.example.student.dto.StudentDto;
import com.example.student.exception.BadRequestException;
import com.example.student.exception.NotFoundException;
import com.example.student.mapper.StudentMapper;
import com.example.student.model.Student;
import com.example.student.request.CreateStudentRequest;
import com.example.student.request.UpdateStudentRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private ModelMapper modelMapper;

    // Tạo danh sách students
    private List<Student> students;

    // Insert 1 số bản ghi student vào trong danh sách
    public StudentService() {
        students = new ArrayList<>();
        students.add(new Student(1, "Nguyễn Văn A", "a@gmail.com", "111"));
        students.add(new Student(2, "Trần Văn B", "b@gmail.com", "222"));
        students.add(new Student(3, "Ngô Thị C", "c@gmail.com", "333"));
        students.add(new Student(4, "Bùi Văn D", "d@gmail.com", "444"));
    }

    // Lấy danh sách tất cả sinh viên
    public List<StudentDto> getAll() {
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    // Lấy thông tin sinh viên theo id
    public StudentDto getStudentById(int id) {
//        Optional<Student> studentOptional = findById(id);
//
//        if(studentOptional.isPresent()) {
//            Student student = studentOptional.get();
//            return modelMapper.map(student, StudentDto.class);
//        }
//
//        throw new NotFoundException("Không có sinh viên có id = " + id);

        Student student = findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không có sinh viên có id = " + id);
        });

        return modelMapper.map(student, StudentDto.class);
    }


    public void deleteStudent(int id) {
        // Kiểm tra xem id có tồn tại hay không
        // Nếu có -> xóa
        // Nếu không -> throw exception
    }

    public List<StudentDto> searchStudent(String name) {
        // Tìm các học viên trong tên có chứa từ khóa cần tìm kiếm (không phân biệt hoa thường)
        return null;
    }

    public StudentDto createStudent(CreateStudentRequest request) {
        // Nếu tồn tại email thì báo lỗi
        if(findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Tạo đối tượng student mới
        Random rd = new Random();
        Student student = new Student();

        student.setId(rd.nextInt(100));
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword());

        // Thêm vào trong list
        students.add(student);

        // Trả về đối tượng DTO
        return modelMapper.map(student, StudentDto.class);
    }

    public StudentDto updateStudent(int id, UpdateStudentRequest request) {
        return null;
    }

    // Helper method
    public Optional<Student> findById(int id) {
        return students
                .stream()
                .filter(student -> student.getId() == id).findFirst();
    }

    public Optional<Student> findByEmail(String email) {
        return students
                .stream()
                .filter(student -> student.getEmail().equals(email)).findFirst();
    }
}
