package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Dtos.CourseDto;
import com.spring_test.test.Entities.Dtos.GetDtos.CourseGetDto;
import com.spring_test.test.Entities.Professor;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Repositories.CourseRepository;
import com.spring_test.test.Repositories.ProfessorRepository;
import com.spring_test.test.Repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UniversityRepository universityRepository;
    private final ProfessorRepository professorRepository;

    public CourseService(CourseRepository courseRepository, UniversityRepository universityRepository, ProfessorRepository professorRepository){
        this.courseRepository = courseRepository;
        this.universityRepository = universityRepository;
        this.professorRepository = professorRepository;
    }

    public CourseGetDto get(final Integer id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
        return new CourseGetDto(course);
    }

    public List<CourseGetDto> getAll(){
        return courseRepository.findAll().stream().map(CourseGetDto::new).toList();
    }

    public CourseGetDto create(CourseDto courseDto, final Integer universityId, final Integer professorId){
        if(professorId == 0){
            throw new BadRequestException("must have a professor");
        }

        if(courseDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University", "Id", universityId));
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));

        var course = Course.builder()
                .Name(courseDto.getName())
                .Description(courseDto.getDescription())
                .university(university)
                .Users(new ArrayList<>())
                .Professors(new ArrayList<>())
                .build();

        course.getProfessors().add(professor);
        course.setCreatedAt(LocalDateTime.now());
        course.setLastModifiedAt(LocalDateTime.now());
        courseRepository.save(course);

        return new CourseGetDto(course);
    }

    public CourseGetDto addProfessor(final Integer courseId, final Integer professorId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));

        if(course.getProfessors().contains(professor)){
            throw new BadRequestException("Professor already in course");
        }

        course.getProfessors().add(professor);
        courseRepository.save(course);

        return new CourseGetDto(course);
    }

    public CourseGetDto update(CourseDto courseDto, final Integer id){
        if(courseDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));

        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());

        courseRepository.save(course);

        return new CourseGetDto(course);
    }

    public void delete(final Integer id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
        courseRepository.delete(course);
    }
}
