package com.spring_test.test.Repositories;

import com.spring_test.test.Entities.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Integer> {

}
