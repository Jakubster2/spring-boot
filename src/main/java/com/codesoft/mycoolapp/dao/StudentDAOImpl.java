package com.codesoft.mycoolapp.dao;

import com.codesoft.mycoolapp.entity.Student;
import jakarta.persistence.EntityManager;
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
    public Student findStudent(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> q1 = entityManager.createQuery("FROM Student", Student.class);
        return q1.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> q1 = entityManager.createQuery("FROM Student where lastName=:theData", Student.class);
        q1.setParameter("theData", lastName);
        return q1.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        Student studentToDelete = entityManager.find(Student.class, id);
        entityManager.remove(studentToDelete);
//        Query q1 = entityManager.createQuery("delete from Student where id=:theData");
//        q1.setParameter("theData", id);
//        q1.executeUpdate();
    }

    @Override
    @Transactional
    public int deleteAll() {
        int q1 = entityManager.createQuery("delete from Student").executeUpdate();
        return q1;
    }
}
