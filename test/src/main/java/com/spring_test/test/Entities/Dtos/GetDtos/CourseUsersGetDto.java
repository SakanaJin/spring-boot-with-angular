package com.spring_test.test.Entities.Dtos.GetDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseUsersGetDto {
    private Integer Id;
    private String UserName;
    private String FirstName;
    private String LastName;
}
