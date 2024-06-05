package com.spring_test.test.Controllers;

import com.spring_test.test.Entities.Course;
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
    public CourseGetDto get(@PathVariable Integer id){
        return courseService.get(id);
    }

    @GetMapping
    public List<CourseGetDto> getAll(){
        return courseService.getAll();
    }

    @PostMapping("/university/{universityId}/professor/{professorId}")
    public ResponseEntity<CourseGetDto> create(@RequestBody CourseDto courseDto, @PathVariable Integer universityId, Integer professorId){
        return new ResponseEntity<CourseGetDto>(courseService.create(courseDto, universityId, professorId), HttpStatus.CREATED);
    }

    @PostMapping("/{courseId}/professor/{professorId}")
    public ResponseEntity<CourseGetDto> addProfessor(@PathVariable final Integer courseId, final Integer professorId){
        return new ResponseEntity<CourseGetDto>(courseService.addProfessor(courseId, professorId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseGetDto> update(@RequestBody CourseDto courseDto, @PathVariable Integer id){
        return new ResponseEntity<CourseGetDto>(courseService.update(courseDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
