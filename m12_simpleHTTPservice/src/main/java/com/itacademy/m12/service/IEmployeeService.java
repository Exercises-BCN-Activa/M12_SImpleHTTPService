package com.itacademy.m12.service;

import java.util.List;

import com.itacademy.m12.dto.Employee;

public interface IEmployeeService {
	
	public Employee createEmployee(Employee employee);

	public List<Employee> readAllEmployee();  
	
	public Employee readById(Long id);
	
	public List<Employee> readByName(String name);
	
	public Employee updateEmployee(Employee employee);
	
	public void deleteById(Long id);

}
