package com.spring.university.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.university.enums.UniversityType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "university", schema = "University")
public class University implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id",nullable = false, unique = true)
    private Integer universityId;

    @Column(name = "university_name",nullable = false,unique = true)
    @NotEmpty(message = "University name should not be empty.")
    @Size(min = 4, max = 40, message = "Must have name between 4 and 40 character long")
    @NotBlank(message = "University name should not be blank.")
    private String universityName;

    @Column(name = "university_addess",nullable = false)
    @NotEmpty(message = "University address should not be empty.")
    @Size(min = 10, max = 40, message = "Must have name between 10 and 40 character long")
    @NotBlank(message = "University address should not be blank.")
    private String address;

    @Column(name = "university_type",nullable = false)
    private UniversityType universityType;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH},orphanRemoval = true, mappedBy = "university")
    private Set<Department> departments;

    @JsonManagedReference
    @OneToMany(orphanRemoval = true,mappedBy = "university")
    private List<Student> students;

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UniversityType getUniversityType() {
        return universityType;
    }

    public void setUniversityType(UniversityType universityType) {
        this.universityType = universityType;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public boolean containsDepartment(){
        return this.departments!=null;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityId=" + universityId +
                ", universityName='" + universityName + '\'' +
                ", address='" + address + '\'' +
                ", universityType=" + universityType +
                '}';
    }
}
