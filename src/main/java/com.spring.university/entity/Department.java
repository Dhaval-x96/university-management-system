package com.spring.university.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Column(name = "department_name",nullable = false)
    @NotEmpty(message = "Department name should not be empty.")
    @NotBlank(message = "Department name should not contain blank space as value.")
    @Size(min = 2,max = 20 ,message = "Department name should be greater then 2 and less then 20.")
    private String departmentName;

    @Column(name = "head_of_department",nullable = false)
    @NotEmpty(message = "HOD name should not be empty.")
    @NotBlank(message = "HOD name should not contain blank space as value.")
    @Size(min = 2,max = 20,message = "HOD name should be greater than 2 but less then 20.")
    private String headOfDepartment;

    @Column(name = "total_number_of_professor",nullable = false)
    @Positive(message = "Please enter value zero or greater then it.")
    private Integer totalNumberOfProfessors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Integer getTotalNumberOfProfessors() {
        return totalNumberOfProfessors;
    }

    public void setTotalNumberOfProfessors(Integer totalNumberOfProfessors) {
        this.totalNumberOfProfessors = totalNumberOfProfessors;
    }

//    public University getUniversity() {
//        return university;
//    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", headOfDepartment='" + headOfDepartment + '\'' +
                ", totalNumberOfProfessors=" + totalNumberOfProfessors +
                '}';
    }
}
