package com.itacademy.m12.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itacademy.m12.dto.Employee;

/**
 * @author FaunoGuazina Data Access Object Class, extends JpaRepository by
 *         Employee and Long id parameters. Establishes two more methods:
 *         findByJob(String) and findByName(String)
 */
public interface IEmployeeDAO extends JpaRepository<Employee, Long> {

	public List<Employee> findByJob(String job); // find employee by name

	public List<Employee> findByName(String name); // find employee by job

}
