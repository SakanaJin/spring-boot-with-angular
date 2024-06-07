package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Dtos.CourseDto;
import com.spring_test.test.Entities.Professor;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Entities.User;
import com.spring_test.test.Repositories.CourseRepository;
import com.spring_test.test.Repositories.ProfessorRepository;
import com.spring_test.test.Repositories.UniversityRepository;
import com.spring_test.test.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UniversityRepository universityRepository;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UniversityRepository universityRepository, ProfessorRepository professorRepository, UserRepository userRepository){
        this.courseRepository = courseRepository;
        this.universityRepository = universityRepository;
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
    }

    public Course get(final Integer id){
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
    }
    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course create(CourseDto courseDto, final Integer universityId, final Integer professorId){
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
        return courseRepository.save(course);
    }

    public Course addProfessor(final Integer courseId, final Integer professorId){
        if(courseId == 0){
            throw new BadRequestException("course id must not be empty");
        }

        if(professorId == 0){
            throw new BadRequestException("professor id must not be empty");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));

        if(course.getProfessors().contains(professor)){
            throw new BadRequestException("Professor already in course");
        }

        course.getProfessors().add(professor);
        return courseRepository.save(course);
    }

    public Course addUser(final Integer courseId, final Integer userId){
        if(courseId == 0){
            throw new BadRequestException("course id must not be empty");
        }

        if(userId == 0){
            throw new BadRequestException("professor id must not be empty");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        if(course.getUsers().contains(user)){
            throw new BadRequestException("user already in course");
        }

        course.getUsers().add(user);
        return courseRepository.save(course);
    }

    public Course update(CourseDto courseDto, final Integer id){
        if(courseDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));

        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());

        return courseRepository.save(course);
    }

    public void removeProfessor(final Integer courseId, final Integer professorId){
        if(courseId == 0){
            throw new BadRequestException("course id must not be empty");
        }

        if(professorId == 0){
            throw new BadRequestException("professor id must not be empty");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));

        if(!course.getProfessors().contains(professor)){
            throw new BadRequestException("professor not in course");
        }

        if(course.getProfessors().size() == 1){
            throw new BadRequestException("course must contain a professor");
        }

        course.getProfessors().remove(professor);
        courseRepository.save(course);
    }

    public void removeUser(final Integer courseId, final Integer userId){
        if(courseId == 0){
            throw new BadRequestException("course id must not be empty");
        }

        if(userId == 0){
            throw new BadRequestException("professor id must not be empty");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        if(!course.getUsers().contains(user)){
            throw new BadRequestException("user not in course");
        }

        course.getUsers().remove(user);
        courseRepository.save(course);
    }

    public void delete(final Integer id){
        if(id == 0){
            throw new BadRequestException("id must not be empty");
        }
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
        courseRepository.delete(course);
    }
}
