package com.itacademy.m12.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	 * @param id
	 * @param name
	 * @param job
	 */
	public Employee(Long id, String name, String role) {
		this.id = id;
		this.name = name;
		job = toCareer(role).getPosition();
		salary = toCareer(role).getSalary();
	}

//getters

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

//setters

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param role to set the job
	 */
	public void setJob(String role) {
		job = toCareer(role).getPosition();
		setSalary(role);
	}

	/**
	 * @param role to set the salary
	 */
	public void setSalary(String role) {
		salary = toCareer(role).getSalary();
		setJob(role);
	}

//To String

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name +
				", job=" + job + ", salary=" + salary + "]";
	}
	

//Enumerate class

	/**
	 * @param role
	 * @return CAREER Enum class object
	 */
	public static CAREER toCareer(String role) {
		try {
			return CAREER.valueOf(role.substring(0, 1).toUpperCase());
		} catch (Exception e) {
			System.out.println("error when trying to process employee role");
			return CAREER.X;
		}
	}

	/**
	 * @author FaunoGuazina
	 *
	 */
	private enum CAREER {
		J("Junior", 1000.0), 
		F("Full", 1700.0), 
		S("Senior", 2500.0), 
		M("Master", 3500.0), 
		X("Error", 0.0);

		private CAREER(String position, double salary) {
			this.position = position;
			this.salary = salary;
		}

		public String getPosition() {
			return position;
		}

		public double getSalary() {
			return salary;
		}

		private String position;
		private double salary;
	}


}
