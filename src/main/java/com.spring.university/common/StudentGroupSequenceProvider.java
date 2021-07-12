package com.spring.university.common;

import com.spring.university.entity.Student;
import com.spring.university.entity.University;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentGroupSequenceProvider implements DefaultGroupSequenceProvider<Student> {
    @Override
    public List<Class<?>> getValidationGroups(Student object) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
//        defaultGroupSequence.add(Student.class);
//        if(object !=null && object.getUniversity() != null){
//            defaultGroupSequence.add(University.class);
//        }
        return defaultGroupSequence;
    }
}
