package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Dtos.ProfessorDto;
import com.spring_test.test.Entities.Professor;
import org.springframework.stereotype.Service;
import com.spring_test.test.Repositories.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public List<Professor> getAll(){
        return professorRepository.findAll();
    }

    public Professor get(final Integer id){
        return professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", id));
    }

    public Professor create(ProfessorDto professorDto){
        if(professorDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        var professor = Professor.builder()
                .Name(professorDto.getName())
                .Courses(new ArrayList<Course>())
                .build();

        return professorRepository.save(professor);
    }

    public Professor update(ProfessorDto professorDto, final Integer id){
        if(professorDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", id));

        professor.setName(professorDto.getName());

        return professorRepository.save(professor);
    }

    public void delete(final Integer id){
        if(id == 0){
            throw new BadRequestException("id must not be empty");
        }
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", id));

        professor.getCourses().forEach(x -> {
            if(x.getProfessors().size() == 1){
                throw new BadRequestException("course " + x.getName() + " still reliant on professor");
            }
            else{
                x.getProfessors().remove(professor);
            }
        });

        professorRepository.delete(professor);
    }
}
