package com.codesoft.mycoolapp.dao;

import com.codesoft.mycoolapp.entity.Student;

import java.util.List;

public interface StudentDAO {

    void saveStudent(Student student);
    Student findStudent(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String lastName);
    void update(Student student);
    void deleteStudent(Integer id);
    int deleteAll();
}
