package com.spring.university.services;

import com.spring.university.entity.University;

import java.util.List;

public interface UniversityService {
    List<University> getAll() throws Exception;
    University save(University university) throws Exception;
    University update(University university, Integer universityId) throws Exception;
    void delete(Integer id) throws Exception;
}
