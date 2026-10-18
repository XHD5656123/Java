package com.example.student_demo.controller;


import com.example.student_demo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final Map<Long, Student> students = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentController(){
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        student.setAge(20);
        student.setEmail("zhangsan@examlpe.com");

        students.put(student.getId(), student);
    }

    @GetMapping
    public List<Student> list(){
        return List.copyOf(students.values());
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id){
        Student student = students.get(id);

        if (student == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"学生不存在");
                    }
        return student;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student){
        Long newId = idGenerator.incrementAndGet();
        student.setId((newId));
        students.put(newId, student);

        return student;
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable("id") Long id,
                          @RequestBody Student requestedstudent){
        Student existingstudent = students.get(id);

        if (existingstudent == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "学生不存在");
        }

        existingstudent.setName(requestedstudent.getName());
        existingstudent.setEmail(requestedstudent.getEmail());
        existingstudent.setAge(requestedstudent.getAge());

        return existingstudent;
    }


}
