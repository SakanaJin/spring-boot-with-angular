package com.spring_test.test.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring_test.test.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
