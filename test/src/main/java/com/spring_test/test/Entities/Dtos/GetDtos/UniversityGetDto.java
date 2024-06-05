package com.spring_test.test.Entities.Dtos.GetDtos;

import com.spring_test.test.Entities.University;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UniversityGetDto {
    private Integer Id;
    private String Name;
    private List<UniversityCoursesGetDto> Courses;

    public UniversityGetDto(University university){
        this.Id = university.getId();
        this.Name = university.getName();
        this.Courses = university.getCourses().stream().map(x -> new UniversityCoursesGetDto(
            x.getId(),
            x.getName(),
            x.getDescription(),
            x.getProfessors().stream().map(y -> new CourseProfessorsGetDto(
                y.getId(),
                y.getName()
            )).toList(),
            x.getUsers().stream().map(y -> new CourseUsersGetDto(
                y.getId(),
                y.getUserName(),
                y.getFirstName(),
                y.getLastName()
            )).toList()
        )).toList();
    }
}
