package com.exam.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exam.microservices.model.Student;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(StudentRepository student) {
//String firstName, String lastName, String major, String dob
    return args -> {
    	student.save(new Student("Noor", "mohammwd", "software eng","2/4/2000"));
    	student.save(new Student("farah", "abu", "software eng","7/1/2009"));
    	student.save(new Student("nada", "ibra", "software eng","29/3/2004"));
    	student.save(new Student("sami", "hassan", "software eng","33/2/2001"));
    	student.save(new Student("safa", "ali", "software eng","24/7/2009"));
    	student.save(new Student("hassan", "muneer", "software eng","29/3/2003"));
    	student.findAll().forEach(student1 -> log.info("Preloaded " + student1.toString()));

      
    
      
    };
  }
}