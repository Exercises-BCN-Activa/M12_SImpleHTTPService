package com.itacademy.m12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.m12.dto.Employee;
import com.itacademy.m12.dao.IEmployeeDAO;

@Service
public class EmployeeService {

	@Autowired
	IEmployeeDAO iEmployeeDAO;

	public Employee createEmployee(Employee employee) { // CREATE a new employee
		return iEmployeeDAO.save(employee);
	}

	public List<Employee> readAllEmployee() { // READ/list all employees
		return iEmployeeDAO.findAll();
	}

	public Employee readById(Long id) { // READ one employee by ID
		return iEmployeeDAO.findById(id).get();
	}

	public List<Employee> readByName(String name) { // READ one employee by name
		return iEmployeeDAO.findByName(name);
	}

	public List<Employee> readByJob(String job) { // READ one employee by job
		return iEmployeeDAO.findByJob(job);
	}

	public Employee updateEmployee(Employee employee) { // UPDATE one employee by ID
		return iEmployeeDAO.save(employee);
	}

	public void deleteById(Long id) { // DELETE one employee by ID
		iEmployeeDAO.deleteById(id);
	}

}
