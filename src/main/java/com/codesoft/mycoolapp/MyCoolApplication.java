package com.codesoft.mycoolapp;

import com.codesoft.mycoolapp.dao.StudentDAO;
import com.codesoft.mycoolapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MyCoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCoolApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
		};
	}


	public void createStudent(StudentDAO studentDAO) {
		Student student = new Student("Klaudia", "Potok","claudia@poczta.fm");
		studentDAO.saveStudent(student);
		System.out.println("Save student to the database");

		Student myStudent = studentDAO.findStudent(student.getId());
		System.out.println("Find student: " + myStudent);

		//find all student
		List<Student> myAllStudent = studentDAO.findAll();

		for(Student tempStudent : myAllStudent) {
			System.out.println(tempStudent);
		}

		List<Student> foundStudent = studentDAO.findByLastName("Potok");

		System.out.println(foundStudent);
		//deleting all user
//		studentDAO.deleteAllUsers();
	}

}
