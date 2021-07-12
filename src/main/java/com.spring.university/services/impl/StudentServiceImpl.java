package com.spring.university.services.impl;

import com.spring.university.entity.Student;
import com.spring.university.repository.StudentRepository;
import com.spring.university.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student save(Student student, Integer universityId) throws Exception {
        return this.studentRepository.save(student,universityId);
    }

    @Override
    public Set<Student> getAll() throws Exception {
        return this.studentRepository.getAll();
    }

    @Override
    public Student update(Student student, Integer studentId) throws Exception {
        return this.studentRepository.update(student,studentId);
    }

    @Override
    public void delete(Integer Id) throws Exception {
        this.studentRepository.delete(Id);
    }
}
