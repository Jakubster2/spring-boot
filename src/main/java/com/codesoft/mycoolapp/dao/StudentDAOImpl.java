package com.codesoft.mycoolapp.dao;

import com.codesoft.mycoolapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StudentDAOImpl implements StudentDAO{

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void deleteAllUsers() {
        Query q1 = entityManager.createQuery("delete from Student ");
        try {
            q1.executeUpdate();
        } catch (SecurityException | IllegalStateException | RollbackException e){
            e.printStackTrace();
        }
    }

    @Override
    public Student findStudent(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> q1 = entityManager.createQuery("FROM Student", Student.class);
        return q1.getResultList();
    }
}
