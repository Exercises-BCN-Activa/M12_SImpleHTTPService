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
		System.out.println(employee);
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
		return employeeServiceImplements.readByName(Util.toTitleCase(name));
	}

	@GetMapping("/employees/job/{job}")
	public List<Employee> readByJob(@PathVariable(name="job") String job) {
		return employeeServiceImplements.readByJob(Util.toTitleCase(job));
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
	
	private static class Util {
		
		/**
		 * method that converts a string to TitleCase, 
		 * that is, each of the words in the string 
		 * will have the first capital letter 
		 * and all other lower case letters.
		 * @param input string you want to convert
		 * @return this same string capitalized only the first letters of each word
		 */
		public static String toTitleCase(String input) {
		    StringBuilder titleCase = new StringBuilder(input.length());
		    boolean nextTitleCase = true;
		    for (Character c : input.toCharArray()) {
	    		if (c.equals(' ') || c.equals('-') || c.equals('\'')) {
	    			nextTitleCase = true;
	    		} else if (nextTitleCase) {
	    			c = Character.toUpperCase(c);
	    			nextTitleCase = false;
	    		} else {
	    			c = Character.toLowerCase(c);
	    		}
	    		titleCase.append(c);
	    	}
		    return titleCase.toString();
		}
		
	}
}
