package com.exam.microservices;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exam.microservices.model.Student;


@RestController
public class StudentController {
	private final StudentRepository repository;

	private final StudentModelAssembler assembler;
	@Autowired
	StudentController(StudentRepository repository, StudentModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}
	
	//Q1: Implement all of the CRUD operations to the student object
	//a) get all
	
	
	
	//b)create student
	
	
	
	//c) get student by id
	
	
	
	//d) update student
	
	
	
	
	//e) delete student, you can only delete a student if they paid all the tuition
	
//create student
/*	@PostMapping("/students")
	private ResponseEntity<?> newStudent( @RequestBody Student entity) {

		EntityModel<Student> entityModel = assembler.toModel(repository.save(entity));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}*/
	@PostMapping("/students")
	private ResponseEntity<?> newOrders(@RequestBody Student entity) {

		EntityModel<Student> entityModel = assembler.toModel(repository.save(entity));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}


	// update student

	@PutMapping("/students/{id}")
	ResponseEntity<?> replaceStudens(@RequestBody Student newStudent,
			@PathVariable(value = "id") Long id) {

		Student updatedStudent = repository.findById(id) //
				.map(student -> {
					student.setFirstName(newStudent.getFirstName());
					student.setLastName(newStudent.getLastName());
					student.setMajor(newStudent.getMajor());
					student.setId(newStudent.getId());
				student.setDob(newStudent.getDob());

					return repository.save(student);
				}) //

				.orElseGet(() -> {
					newStudent.setId(id);
					return repository.save(newStudent);
				});

		EntityModel<Student> entityModel = assembler.toModel(updatedStudent);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	
//get student by id
	@GetMapping(value = "/students/{id}")
	public EntityModel<Student> findById(@PathVariable Long id) {

		Student student = repository.findById(id) //
				.orElseThrow(() -> new StudentNotFound(id));

		return EntityModel.of(student, //
				linkTo(methodOn(StudentController.class).findById(student.getId())).withSelfRel(),
				linkTo(methodOn(StudentController.class).findAll()).withRel("students"));
	}
	
	//get all student
	@GetMapping( "/students")
	public	CollectionModel<EntityModel<Student>> findAll() {

	  List<EntityModel<Student>> students = repository.findAll().stream()
	      .map(student-> EntityModel.of(student,
	          linkTo(methodOn(StudentController.class).findById(student.getId())).withSelfRel(),
	          linkTo(methodOn(StudentController.class).findAll()).withRel("payments")))
	      .collect(Collectors.toList());

	  return CollectionModel.of(students , linkTo(methodOn(StudentController.class).findAll()).withSelfRel());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/students/{id}")
	ResponseEntity<?> deleteStudent(@PathVariable Long id) {

	  repository.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
}
