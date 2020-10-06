package com.itacademy.m12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.m12.dto.Employee;
import com.itacademy.m12.service.EmployeeServiceImplements;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImplements employeeServiceImplements;
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeServiceImplements.createEmployee(employee);
	}

	@GetMapping("/employees")
	public List<Employee> readAllEmployee() {
		return employeeServiceImplements.readAllEmployee();
	}

	@GetMapping("/employees/{id}")
	public Employee readById(@PathVariable(name="id") Long id) {
		Employee employee = employeeServiceImplements.readById(id);
		System.out.println(employee);
		return employee;
	}

	@GetMapping("/employees/name/{name}")
	public List<Employee> readByName(@PathVariable(name="name") String name) {
		return employeeServiceImplements.readByName(name);
	}

	@GetMapping("/employees/job/{role}")
	public List<Employee> readByJob(@PathVariable(name="role") String role) {
		return employeeServiceImplements.readByJob(role);
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(name="id")Long id, 
									@RequestBody Employee employee) {
		Employee selected = employeeServiceImplements.readById(id);
		System.out.println("SELECTED: " + selected);
		selected.setName(employee.getName());
		selected.setJob(employee.getJob());
		Employee updated = employeeServiceImplements.updateEmployee(selected);
		System.out.println("UPDATED: " + updated);
		return updated;
	}

	@DeleteMapping("/employees/{id}")
	public void deleteById(@PathVariable(name="id") Long id) {
		employeeServiceImplements.deleteById(id);
	}
	

}
