package com.exam.microservices.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Student {
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String major;
	private String dob;

	public Student() {

	}

	public Student(String firstName, String lastName, String major, String dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.major = major;
		this.dob = dob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}


}
