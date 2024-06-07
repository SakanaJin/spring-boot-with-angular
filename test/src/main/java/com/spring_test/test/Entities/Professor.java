package com.spring_test.test.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnoreProperties("professors")
    private List<Course> Courses;
}
