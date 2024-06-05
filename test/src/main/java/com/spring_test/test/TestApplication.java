package com.spring_test.test;

import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Repositories.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import java.time.LocalDateTime;

@SpringBootApplication

public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	//@Bean
	public CommandLineRunner commandLineRunner(
		CourseRepository courseRepository
	) {
		return args -> {

		};
	}
}
