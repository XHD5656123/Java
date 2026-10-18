package com.example.student_demo.controller;


import com.example.student_demo.entity.Student;
import com.example.student_demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
//StudentController → StudentService → StudentMapper → PostgreSQL
//GET /api/students
//→ StudentController.list()
//→ studentService.findAll()
//→ studentMapper.selectList(null)
//→ PostgreSQL
//→ 返回 List<Student>
@RestController
@RequestMapping("/api/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> list(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id){
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student){
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,
                          @Valid @RequestBody Student student){
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        studentService.delete(id);
        }
}
