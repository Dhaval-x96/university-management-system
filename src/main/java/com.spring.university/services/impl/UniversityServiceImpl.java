package com.spring.university.services.impl;


import com.spring.university.repository.UniversityRepository;
import com.spring.university.entity.University;
import com.spring.university.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<University> getAll()throws Exception {
        return this.universityRepository.findAll();
    }

    @Override
    public University save(University university) throws Exception{

        if(university.containsDepartment()){
            university.getDepartments().forEach(e -> e.setUniversity(university));
        }

        this.universityRepository.save(university);
        return university;
    }

    @Override
    public University update(University university, Integer id) throws Exception {
       return this.entityManager.merge(university);
    }

    @Override
    public void delete(Integer id)throws Exception{
           Optional<University>  optionalUniversity =  this.universityRepository.findById(id);
            if(optionalUniversity.isPresent()){
                this.universityRepository.delete(optionalUniversity.get());
            } else {
                throw new RuntimeException("University does not exist for Id: "+id);
            }

    }
}
