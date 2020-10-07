package com.itacademy.m12.controller;

import java.util.List;

import javax.servlet.ServletException;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.HttpStatus;

import com.itacademy.m12.dto.Employee;
import com.itacademy.m12.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController { // class that controls the entire application

	@Autowired
	EmployeeService service; // service instance

	/**
	 * Create a new Employee in data base by path "/api/employees"
	 * 
	 * @param employee by json format
	 * @return that employee inserted
	 */
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return service.createEmployee(employee);
	}

	/**
	 * Read all employees from DataBases
	 * 
	 * @return list of employees
	 */
	@GetMapping("/employees")
	public List<Employee> readAllEmployee() {
		return service.readAllEmployee();
	}

	/**
	 * Read one employee from DataBase by ID
	 * 
	 * @param id number
	 * @return employee object
	 */
	@GetMapping("/employees/{id}")
	public Employee readById(@PathVariable(name = "id") Long id) {
		return service.readById(id);
	}

	/**
	 * Read one employee from DataBase by NAME
	 * 
	 * @param name string
	 * @return employee object
	 */
	@GetMapping("/employees/name/{name}")
	public List<Employee> readByName(@PathVariable(name = "name") String name) {
		return service.readByName(Util.toTitleCase(name));
	}

	/**
	 * Read one employee from DataBase by JOB
	 * 
	 * @param job string
	 * @return employee object
	 */
	@GetMapping("/employees/job/{job}")
	public List<Employee> readByJob(@PathVariable(name = "job") String job) {
		return service.readByJob(Util.toTitleCase(job));
	}

	/**
	 * Update one employee from DataBase by ID
	 * 
	 * @param id number
	 * @param employeeUpdated by json format
	 * @return that employee object updated
	 */
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employeeUpdated) {
		Employee employeeToUpdate = service.readById(id);
		employeeToUpdate.setName(employeeUpdated.getName());
		employeeToUpdate.setJob(employeeUpdated.getJob());
		return service.updateEmployee(employeeToUpdate);
	}

	/**
	 * Delete one employee from DataBase by ID
	 * 
	 * @param id number
	 */
	@DeleteMapping("/employees/{id}")
	public void deleteById(@PathVariable(name = "id") Long id) {
		service.deleteById(id);
	}

	/**
	 * Class of methods to deal with errors
	 * 
	 * @author FaunoGuazina
	 */
	@ControllerAdvice
	private class ControllerHandlerException extends DefaultResponseErrorHandler {

		@ExceptionHandler(ServletException.class)
		@ResponseStatus(value = HttpStatus.NOT_FOUND)
		@ResponseBody
		public String requestNotFound() {
			return "[NOT FOUND . 404] -> CAN'T FOUND ANYTHING BABY!!!";
		}

		@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
		@ResponseStatus(value = HttpStatus.BAD_REQUEST)
		@ResponseBody
		public String invalidRequest() {
			return "[BAD REQUEST . 400] -> Invalid or incorrect resquisition!!!";
		}

		@ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		@ResponseBody
		public String cantFoundEmployee() {
			return "[INTERNAL SERVER ERROR . 500] -> Could not find this employee!!!";
		}
	}

	/**
	 * Class of auxiliary methods to deal with inputs and / or treatment of strings
	 * 
	 * @author FaunoGuazina
	 */
	private static class Util {

		/**
		 * method that converts a string to TitleCase, that is, each of the words in the
		 * string will have the first capital letter and all other lower case letters.
		 * 
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
