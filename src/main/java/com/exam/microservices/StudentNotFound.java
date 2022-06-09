package com.exam.microservices;

public class StudentNotFound extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentNotFound(Long id) {
		super("Could not find student " + id);
	}
}
