package com.spring_test.test.Entities;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
@Entity
public class User {
    @jakarta.persistence.Id
    @GeneratedValue
    private Integer Id;
    private String UserName;
    private String FirstName;
    private String LastName;
    private String Email;
    @ManyToMany(mappedBy = "Users")
    private List<Course> Courses;
}
