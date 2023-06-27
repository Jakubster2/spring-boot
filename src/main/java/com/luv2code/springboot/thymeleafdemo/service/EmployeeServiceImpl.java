package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import com.luv2code.springboot.thymeleafdemo.error.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	public List<Employee> findAllByOrderByLastName() {
		return employeeRepository.findAllByOrderByLastName();
	}

	@Override
	public Employee update(Employee employee) {
		Optional<Employee> theEmployee = employeeRepository.findById(employee.getId());
		if(!theEmployee.isPresent())
			throw new EmployeeNotFoundException("Employee Not Found");

		if(employee.getFirstName() != null && !(employee.getFirstName().isBlank()))
			theEmployee.get().setFirstName(employee.getFirstName());
		if(employee.getLastName() != null && !(employee.getLastName().isBlank()))
			theEmployee.get().setLastName(employee.getLastName());
		if(employee.getEmail() != null && !(employee.getEmail().isBlank()))
			theEmployee.get().setEmail(employee.getEmail());

		return employeeRepository.save(theEmployee.get());
	}

}






