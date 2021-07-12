package com.spring.university.controller;

import com.spring.university.common.BasicValidation;
import com.spring.university.common.Utilities;
import com.spring.university.entity.Student;
import com.spring.university.json.ApiResponse;
import com.spring.university.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<Set<Student>>> getAll(HttpServletRequest request){
        try{
            Set<Student> response = this.studentService.getAll();
            return new ResponseEntity<>(new ApiResponse(response, request.getRequestURL().toString()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Utilities.getExceptionObject(request, HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("university/{universityId}")
    public ResponseEntity<ApiResponse<Student>> save(@PathVariable(value = "universityId") Integer universityId,
                                            @Validated(BasicValidation.class) @RequestBody(required = true) Student student,
                                            HttpServletRequest request){
        try{
            Student response = this.studentService.save(student,universityId);
            return new ResponseEntity<>(new ApiResponse(response, request.getRequestURL().toString()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Utilities.getExceptionObject(request, HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{studentId}")
    public ResponseEntity<ApiResponse<Student>> update(@Validated(BasicValidation.class) @PathVariable(value = "studentId", required = true) Integer studentId,
                                                        @RequestBody Student student,
                                                        HttpServletRequest request){
        try {
            Student response = this.studentService.update(student,studentId);
            return new ResponseEntity<>(new ApiResponse(response, request.getRequestURL().toString()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Utilities.getExceptionObject(request, HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<ApiResponse<Student>> delete(@PathVariable("studentId") Integer studentId,
                                                       HttpServletRequest request){
        try {
            this.studentService.delete(studentId);
            return new ResponseEntity<>(new ApiResponse("Successfully deleted", request.getRequestURL().toString()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Utilities.getExceptionObject(request, HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
