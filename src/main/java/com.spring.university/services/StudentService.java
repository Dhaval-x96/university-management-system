package com.spring.university.services;

import com.spring.university.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Student save(Student student, Integer universityId) throws Exception;
    Set<Student> getAll() throws Exception;
    Student update(Student student,Integer studentId) throws Exception;
    void delete(Integer Id) throws Exception;
}
