package com.example.student_demo.service.impl;


import com.example.student_demo.entity.Student;
import com.example.student_demo.mapper.StudentMapper;
import com.example.student_demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Student> findAll(){
        return studentMapper.selectList(null);
    }

    @Override
    public Student findById(Long id) {
        Student student = studentMapper.selectById(id);
        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "学生不存在"
            );
        }
        return student;
    }

    @Override
    public Student create(Student student){
        student.setId(null);
//        ID 由数据库BIGSERIAL生成，客户端无需传入
        studentMapper.insert(student);
        return student;
    }

    @Override
    public Student update(Long id, Student request){
        Student existing = findById(id);

        existing.setName(request.getName());
        existing.setAge(request.getAge());
        existing.setEmail(request.getEmail());

        studentMapper.updateById(existing);
        return existing;

    }

    @Override
    public void delete(Long id){
        findById(id);
//      先确认记录存在，不存在时返回404
        studentMapper.deleteById(id);
    }
}
