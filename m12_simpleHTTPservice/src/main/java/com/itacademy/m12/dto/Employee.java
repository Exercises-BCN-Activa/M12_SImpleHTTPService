package com.itacademy.m12.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author FaunoGuazina Data Transfer Object Class, have enum inner class to
 *         define job and salary variables.
 */
@Entity
@Table(name = "employee")
public class Employee {

//class entity attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "job")
	private String job;
	@Column(name = "salary")
	private double salary;

//constructors

	public Employee() {

	}

	/**
	 * @param id   long number
	 * @param name string
	 * @param job  string options {J for ("Junior"), F for ("Full"), S for
	 *             ("Senior", M for ("Master"} you can write the whole word, but
	 *             only the first letter will be valid
	 */
	public Employee(Long id, String name, String job) {
		this.id = id;
		this.name = name;
		CAREER career = toCareer(job);
		this.job = career.getPosition();
		this.salary = career.getSalary();
	}

//getters

	/**
	 * @return the id - Long Number
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name - String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the job - String
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @return the salary - Double Number
	 */
	public double getSalary() {
		return salary;
	}

//setters

	/**
	 * @param id number to set the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name string to set the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param role string to set the job and salary string options are: {J for
	 *             ("Junior"), F for ("Full"), S for ("Senior", M for ("Master"} you
	 *             can write the whole word, but only the first letter will be valid
	 */
	public void setJob(String job) {
		CAREER career = toCareer(job);
		this.job = career.getPosition();
		this.salary = career.getSalary();
	}

//To String

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", job=" + job + ", salary=" + salary + "]";
	}

	/**
	 * Method which converts string to enum CAREER.
	 * 
	 * @param role string: select the first letter, convert it to a capital letter
	 *             and submit to the method valueOf
	 * @return CAREER Enum class object, if you don't find a valid option it returns
	 *         an error enum X with values "Error" to job and 0.0 to salary.
	 */
	private CAREER toCareer(String role) {
		try {
			return CAREER.valueOf(role.substring(0, 1).toUpperCase());
		} catch (Exception e) {
			System.out.println("error when trying to process employee role");
			return CAREER.X;
		}
	}

	/**
	 * @author FaunoGuazina Private Inner Class: Enumerate class establishing the
	 *         work and salary of each employee
	 */
	private enum CAREER {
		// definition of possibles careers and salaries.
		J("Junior", 1000.0), F("Full", 1700.0), S("Senior", 2500.0), M("Master", 3500.0), X("Error", 0.0);

		// constructor
		private CAREER(String position, double salary) {
			this.position = position;
			this.salary = salary;
		}

		// getters
		public String getPosition() {
			return position;
		}

		public double getSalary() {
			return salary;
		}

		// declaration of variables
		private String position;
		private double salary;
	}

}
