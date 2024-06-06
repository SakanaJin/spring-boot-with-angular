package com.spring_test.test;

import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Professor;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Repositories.CourseRepository;
import com.spring_test.test.Repositories.ProfessorRepository;
import com.spring_test.test.Repositories.UniversityRepository;
import com.spring_test.test.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring_test.test.Entities.User;



import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication

public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		CourseRepository courseRepository,
		ProfessorRepository professorRepository,
		UniversityRepository universityRepository,
		UserRepository userRepository
	) {
		return args -> {
			var professor1 = Professor.builder()
					.Name("Dr. Nuerberg")
					.Courses(new ArrayList<>())
					.build();
			var university1 = University.builder()
					.Name("SELU")
					.Courses(new ArrayList<>())
					.build();
			var course1 = Course.builder()
					.Name("Math 200")
					.Description("Calc 1")
					.Professors(new ArrayList<>())
					.university(university1)
					.Users(new ArrayList<>())
					.build();
			var user1 = User.builder()
					.UserName("Prince_of_All_Saiyans")
					.FirstName("Vegeta")
					.LastName("TheFourth")
					.Email("PrinceOfAllSaiyans@Frieza.com")
					.Courses(new ArrayList<>())
					.build();

			professorRepository.save(professor1);
			universityRepository.save(university1);
			userRepository.save(user1);
			course1.getUsers().add(user1);
			course1.getProfessors().add(professor1);
			courseRepository.save(course1);
		};
	}
}
