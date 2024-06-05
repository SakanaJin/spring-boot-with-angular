package com.spring_test.test.Entities.Dtos.GetDtos;

import com.spring_test.test.Entities.User;
import lombok.Data;

import java.util.List;

@Data
public class UserGetDto {
    private Integer Id;
    private String UserName;
    private String FirstName;
    private String LastName;
    private String Email;
    private List<UserCoursesGetDto> Courses;

    public UserGetDto(User user){
        this.Id = user.getId();
        this.UserName = user.getUserName();
        this.FirstName = user.getFirstName();
        this.LastName = user.getLastName();
        this.Email = user.getEmail();
        this.Courses = user.getCourses().stream().map(x -> new UserCoursesGetDto(
            x.getId(),
            x.getName(),
            x.getDescription(),
            x.getUniversity().getId(),
            x.getProfessors().stream().map(y -> new CourseProfessorsGetDto(
               y.getId(),
               y.getName()
            )).toList()
        )).toList();
    }
}
