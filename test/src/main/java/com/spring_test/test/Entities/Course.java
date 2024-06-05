package com.spring_test.test.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
@Entity
public class Course extends BaseEntity{
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
    private List<User> Users;
    @ManyToOne
    @JoinColumn(name="UniversityId")
    private University university;
}

