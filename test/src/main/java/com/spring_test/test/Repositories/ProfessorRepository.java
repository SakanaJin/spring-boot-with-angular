package com.spring_test.test.Repositories;

import com.spring_test.test.Entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
