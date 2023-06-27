package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		Employee theEmployee = new Employee();
		model.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("employeeId") int theId,
								 Model model) {
		Employee employee = employeeService.findById(theId);
		model.addAttribute("employee",employee);

		return "employees/employee-update-form";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute("employee") Employee employee) {

		employeeService.update(employee);

		return "redirect:/employees/list";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);

		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}


	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAllByOrderByLastName();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
}









