package com.itacademy.m12.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itacademy.m12.dto.Employee;


public interface IEmployeeDAO extends JpaRepository<Employee, Long>{
	
	public List<Employee> findByJob(String job);
	public List<Employee> findByName(String name);

}
