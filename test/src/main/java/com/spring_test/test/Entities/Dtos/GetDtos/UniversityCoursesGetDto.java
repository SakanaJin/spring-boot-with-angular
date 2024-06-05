package com.spring_test.test.Entities.Dtos.GetDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UniversityCoursesGetDto {
    private Integer Id;
    private String Name;
    private String Description;
    private List<CourseProfessorsGetDto> Professors;
    private List<CourseUsersGetDto> Users;
}
