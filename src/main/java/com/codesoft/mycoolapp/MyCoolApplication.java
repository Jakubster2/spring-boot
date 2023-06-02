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
//			createStudent(studentDAO);
//			findAllStudents(studentDAO);
//			findStudentByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteAllStudent(studentDAO);
			deleteUserById(studentDAO);
		};
	}


	public void createStudent(StudentDAO studentDAO) {
		Student student = new Student("Klaudia", "Potok","claudia@poczta.fm");
		studentDAO.saveStudent(student);
		System.out.println("Save student to the database");
	}

	public void findStudentByLastName(StudentDAO studentDAO){
		List<Student> foundStudents = studentDAO.findByLastName("Potok");

		for(Student student:foundStudents) {
			System.out.println("Student: " + student);
		}
	}

	public void findAllStudents(StudentDAO studentDAO){
		List<Student> myAllStudent = studentDAO.findAll();

		for(Student tempStudent : myAllStudent) {
			System.out.println(tempStudent);
		}
	}

	public void updateStudent(StudentDAO studentDAO) {
		Student myNewStudent = studentDAO.findStudent(20);
		myNewStudent.setLastName("Kulesza");
		studentDAO.update(myNewStudent);
		System.out.println("Student updated");
	}

	public void findStudent(StudentDAO studentDAO) {
		int id = 1;
		Student myStudent = studentDAO.findStudent(id);
		System.out.println("Find student: " + myStudent);
	}

	public void deleteAllStudent(StudentDAO studentDAO) {
		int numOfRows = studentDAO.deleteAll();
		System.out.printf("Deleting %s rows", numOfRows);
	}

	public void deleteUserById(StudentDAO studentDAO){
		studentDAO.deleteStudent( 23);
		System.out.println("Student deleted\n");
	}

}
