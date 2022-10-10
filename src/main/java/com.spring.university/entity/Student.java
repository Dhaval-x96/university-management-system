package com.spring.university.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.university.common.AdvanceValidation;
import com.spring.university.common.BasicValidation;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id",nullable = false,unique = true)
    private Integer studentId;

    @Column(name = "student_name",nullable = false,unique = false)
    @NotEmpty(message = "Student name should not be empty.", groups = BasicValidation.class)
    @Size(min = 3, max = 40, message = "Student name must be between 3 and 40 character long.", groups = BasicValidation.class)
    @NotBlank(message = "Student name should not be blank value.", groups = BasicValidation.class)
    private String studentName;


    @Column(name = "student_address",nullable = false,unique = false)
    @NotEmpty(message = "Student address should not be empty.", groups = BasicValidation.class)
    @Size(min = 4, max = 40, message = "Student Address must be between 4 and 40 character long", groups = BasicValidation.class)
    @NotBlank(message = "Student address should not be blank value.", groups = BasicValidation.class)
    private String studentAddress;

    @Column(name = "date_of_birth",nullable = false)
    @Past(message = "Birthday must be in the past", groups = BasicValidation.class)
    private LocalDateTime dateOfBirth;

    @Column(name = "age",nullable = false)
    @Positive(message = "Please enter age greater then zero", groups = BasicValidation.class)
    private Integer age;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    @NotNull(groups = AdvanceValidation.class)
    private University university;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
