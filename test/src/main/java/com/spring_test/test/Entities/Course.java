package com.spring_test.test.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
@Entity
public class Course{
    @jakarta.persistence.Id
    @GeneratedValue
    private Integer Id;
    private String Name;
    private String Description;
    @ManyToMany
    @JoinTable(
        name = "CourseProfessors",
        joinColumns = {
            @JoinColumn(name = "CourseId")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "ProfessorId")
        }
    )
    @JsonIgnoreProperties("courses")
    private List<Professor> Professors;
    @ManyToMany
    @JoinTable(
        name = "CourseUsers",
        joinColumns = {
            @JoinColumn(name = "CourseId")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "UserId")
        }
    )
    @JsonIgnoreProperties("courses")
    private List<User> Users;
    @ManyToOne
    @JoinColumn(name="UniversityId")
    @JsonIgnoreProperties("courses")
    private University university;
}

