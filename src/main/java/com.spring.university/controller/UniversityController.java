package com.spring.university.controller;

import com.spring.university.common.Utilities;
import com.spring.university.json.ApiResponse;
import com.spring.university.entity.University;
import com.spring.university.services.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "university")
@Slf4j
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<University>>> getAllUniversity(HttpServletRequest request){

        try {
            List<University> universities = this.universityService.getAll();
            log.info("Successfully get the universities data.");
            return new ResponseEntity<ApiResponse<List<University>>>(new ApiResponse<List<University>>(universities, request.getRequestURL().toString()), HttpStatus.OK);
        }catch (Exception e){

            log.error(e.getMessage());
            return new ResponseEntity<>(Utilities.getExceptionObject(request,HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<University>> save(@Valid @RequestBody(required = true) University university, HttpServletRequest request){
        try {
            return new ResponseEntity<ApiResponse<University>>(new ApiResponse<University>(this.universityService.save(university),request.getRequestURL().toString()),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Utilities.getExceptionObject(request,HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{universityId}")
    public ResponseEntity<ApiResponse<University>> update(@Valid @RequestBody(required = true) University university
    ,@PathVariable(value = "universityId") Integer Id, HttpServletRequest request){
        try {
            return new ResponseEntity<ApiResponse<University>>(new ApiResponse<University>(this.universityService.update(university,Id), request.getRequestURL().toString()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Utilities.getExceptionObject(request,HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "{universityId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable(value = "universityId") Integer Id,HttpServletRequest request) throws Exception{
        try {
            this.universityService.delete(Id);
            return new ResponseEntity<>(new ApiResponse("Deleted Successfully.", request.getRequestURL().toString()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Utilities.getExceptionObject(request,HttpStatus.INTERNAL_SERVER_ERROR.value(),e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void m1(){
        System.out.println("-------------- m1-----------------");
    }
}
