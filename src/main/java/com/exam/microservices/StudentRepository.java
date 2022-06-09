package com.exam.microservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.microservices.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
