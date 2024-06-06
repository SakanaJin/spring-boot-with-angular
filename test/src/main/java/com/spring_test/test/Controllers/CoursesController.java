package com.spring_test.test.Controllers;

import com.spring_test.test.Entities.Dtos.CourseDto;
import com.spring_test.test.Entities.Dtos.GetDtos.CourseGetDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring_test.test.Services.CourseService;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public CourseGetDto get(@PathVariable final Integer id){
        return courseService.get(id);
    }

    @GetMapping
    public List<CourseGetDto> getAll(){
        return courseService.getAll();
    }

    @PostMapping("/university/{universityId}/professor/{professorId}")
    public ResponseEntity<CourseGetDto> create(@RequestBody CourseDto courseDto, @PathVariable final Integer universityId, final Integer professorId){
        return new ResponseEntity<CourseGetDto>(courseService.create(courseDto, universityId, professorId), HttpStatus.CREATED);
    }

    @PostMapping("/{courseId}/professor/{professorId}")
    public ResponseEntity<CourseGetDto> addProfessor(@PathVariable final Integer courseId, final Integer professorId){
        return new ResponseEntity<CourseGetDto>(courseService.addProfessor(courseId, professorId), HttpStatus.OK);
    }

    @PostMapping("/{courseId}/user/{userId}")
    public ResponseEntity<CourseGetDto> addUser(@PathVariable final Integer courseId, final Integer userId){
        return new ResponseEntity<CourseGetDto>(courseService.addUser(courseId, userId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseGetDto> update(@RequestBody CourseDto courseDto, @PathVariable final Integer id){
        return new ResponseEntity<CourseGetDto>(courseService.update(courseDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/professor/{professorId}")
    public ResponseEntity<HttpStatus> removeProfessor(@PathVariable final Integer courseId, final Integer professorId){
        courseService.removeProfessor(courseId, professorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/user/{userId}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable final Integer courseId, final Integer userId){
        courseService.removeUser(courseId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Integer id){
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
