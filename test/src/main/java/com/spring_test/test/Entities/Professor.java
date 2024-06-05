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
@Table(name="professors")
@Entity
public class Professor {
    @jakarta.persistence.Id
    @GeneratedValue
    private Integer Id;
    private String Name;
    @ManyToMany(mappedBy = "Professors")
    private List<Course> Courses;
}
