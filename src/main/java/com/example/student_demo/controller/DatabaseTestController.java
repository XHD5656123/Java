package com.example.student_demo.controller;

import com.example.student_demo.entity.Student;
import com.example.student_demo.mapper.StudentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseTestController {

    private final StudentMapper studentMapper;

    public DatabaseTestController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @GetMapping("/db-test/students")
    public List<Student> testDatabase() {
        return studentMapper.selectList(null);
    }
}