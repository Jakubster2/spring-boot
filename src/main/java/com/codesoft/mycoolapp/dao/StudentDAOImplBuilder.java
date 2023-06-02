package com.codesoft.mycoolapp.dao;

import jakarta.persistence.EntityManager;

public class StudentDAOImplBuilder {
    private EntityManager entityManager;

    public StudentDAOImplBuilder setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public StudentDAOImpl createStudentDAOImpl() {
        return new StudentDAOImpl(entityManager);
    }
}