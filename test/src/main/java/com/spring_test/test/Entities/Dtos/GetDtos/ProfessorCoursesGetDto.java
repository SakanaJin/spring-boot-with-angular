package com.spring_test.test.Entities.Dtos.GetDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProfessorCoursesGetDto {
    private Integer Id;
    private String Name;
    private String Description;
    private Integer UniversityId;
    private List<CourseUsersGetDto> Users;
}
