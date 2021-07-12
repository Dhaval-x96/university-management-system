package com.spring.university.repository.impl;

import com.spring.university.entity.Student;
import com.spring.university.entity.University;
import com.spring.university.repository.StudentRepository;
import com.spring.university.repository.UniversityRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UniversityRepository universityRepository;

    private  Session getSession(){
       return entityManager.unwrap(Session.class).getSession();
    }

    @Override
    public Student save(Student student, Integer universityId) throws Exception {
             Session session = getSession();
             Optional<University> optionalUniversity = this.universityRepository.findById(universityId);
             if(optionalUniversity.isPresent()){
                 student.setUniversity(optionalUniversity.get());
                 Integer id = (Integer)session.save(student);
                 return  session.find(Student.class, id);
             } else {
                 throw new RuntimeException("University does not exist for id: "+ universityId);
             }
    }

    @Override
    public Set<Student> getAll() throws Exception {
        Session session = getSession();
        return  (Set<Student>) session.createQuery("from Student").getResultStream()
                .collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(Student::getStudentId,(i1,i2)->i1.compareTo(i2)))));
    }

    @Override
    public Student update(Student student, Integer studentId) throws Exception {
            Session session = getSession();
            session.update(student);
            return session.find(Student.class,studentId);
    }

    @Override
    public void delete(Integer Id) throws Exception {
        Session session = getSession();
        session.delete(session.find(Student.class,Id));
    }
}
