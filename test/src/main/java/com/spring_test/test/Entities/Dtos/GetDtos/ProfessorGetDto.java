package com.spring_test.test.Entities.Dtos.GetDtos;

import com.spring_test.test.Entities.Professor;
import lombok.Data;

import java.util.List;

@Data
public class ProfessorGetDto {
    private Integer Id;
    private String Name;
    private List<ProfessorCoursesGetDto> Courses;

    public ProfessorGetDto(Professor professor){
        this.Id = professor.getId();
        this.Name = professor.getName();
        this.Courses = professor.getCourses().stream().map(x -> new ProfessorCoursesGetDto(
            x.getId(),
            x.getName(),
            x.getDescription(),
            x.getUniversity().getId(),
            x.getUsers().stream().map(y -> new CourseUsersGetDto(
               y.getId(),
               y.getUserName(),
               y.getFirstName(),
               y.getLastName()
            )).toList()
        )).toList();
    }
}
