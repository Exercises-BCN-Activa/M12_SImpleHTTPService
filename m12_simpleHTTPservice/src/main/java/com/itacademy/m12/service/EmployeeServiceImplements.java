package com.itacademy.m12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.m12.dto.Employee;
import com.itacademy.m12.dao.IEmployeeDAO;

@Service
public class EmployeeServiceImplements implements IEmployeeService {
	
	@Autowired
	IEmployeeDAO iEmployeeDAO;

	@Override
	public Employee createEmployee(Employee employee) {
		return iEmployeeDAO.save(employee);
	}

	@Override
	public List<Employee> readAllEmployee() {
		return iEmployeeDAO.findAll();
	}

	@Override
	public Employee readById(Long id) {
		return iEmployeeDAO.findById(id).get();
	}

	@Override
	public List<Employee> readByName(String name) {
		return iEmployeeDAO.findByName(name);
	}
	
	public List<Employee> readByJob(String role) {
		return iEmployeeDAO.findByJob(role);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return iEmployeeDAO.save(employee);
	}

	@Override
	public void deleteById(Long id) {
		iEmployeeDAO.deleteById(id);
	}
	
	

}
