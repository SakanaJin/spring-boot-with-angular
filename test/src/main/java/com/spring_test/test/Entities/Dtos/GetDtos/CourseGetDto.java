package com.spring_test.test.Entities.Dtos.GetDtos;

import com.spring_test.test.Entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseGetDto {
    private Integer Id;
    private String Name;
    private String Description;
    private Integer UniversityId;
    private List<CourseProfessorsGetDto> Professors;
    private List<CourseUsersGetDto> Users;

    public CourseGetDto(Course course){
        this.Id = course.getId();
        this.Name = course.getName();
        this.Description = course.getDescription();
        this.UniversityId = course.getUniversity().getId();
        this.Professors = course.getProfessors().stream().map(x -> new CourseProfessorsGetDto(
            x.getId(),
            x.getName()
        )).toList();
        this.Users = course.getUsers().stream().map(x -> new CourseUsersGetDto(
            x.getId(),
            x.getUserName(),
            x.getFirstName(),
            x.getLastName()
        )).toList();
    }
}
