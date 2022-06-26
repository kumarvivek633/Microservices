package com.ms3.controller;

import com.ms3.model.Employee;
import com.ms3.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserController.
 */
@RestController
@CrossOrigin
public class EmployeeController {

	/** The user service. */
	@Autowired
	private EmployeeService employeeService;


	
	@GetMapping({ "/viewEmployees" })
	public List<Employee> viewEmployee(@RequestParam String userName) {
		return employeeService.findByCreatedBy(userName);
	}
	
	@GetMapping({ "/getEmployee" })
	public Employee getEmployee(@RequestParam Long empId) {
		return employeeService.findByEmpId(empId);
	}

	
	@GetMapping(value = "/delete_employee/{empId}")
	public String deleteEmployee(@PathVariable String empId) {
		
		employeeService.deleteByEmpId(Long.valueOf(empId));
		return "success";
	}

	
	
	@PostMapping({ "/addEmp" })
	public String addEmp(@RequestBody Employee empForm) {
		employeeService.save(empForm);
		return "success";
	}

	
	}
