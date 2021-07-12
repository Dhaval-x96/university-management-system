package com.spring.university.services;

import com.spring.university.entity.Department;

import java.util.Set;

public interface DepartmentRepository {
    Set<Department> getAll() throws Exception;
    Department save(Department department, Integer universityId) throws Exception;
    Department update(Department department) throws Exception;
    void delete(Integer departmentId) throws Exception;
}
