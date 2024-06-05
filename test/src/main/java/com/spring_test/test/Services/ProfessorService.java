package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Dtos.GetDtos.ProfessorGetDto;
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

    public List<ProfessorGetDto> getAll(){
        return professorRepository.findAll().stream().map(ProfessorGetDto::new).toList();
    }

    public ProfessorGetDto get(final Integer id){
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", id));
        return new ProfessorGetDto(professor);
    }

    public ProfessorGetDto create(ProfessorDto professorDto){
        if(professorDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        var professor = Professor.builder()
                .Name(professorDto.getName())
                .Courses(new ArrayList<Course>())
                .build();

        professorRepository.save(professor);

        return new ProfessorGetDto(professor);
    }

    public ProfessorGetDto update(ProfessorDto professorDto, final Integer id){
        if(professorDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", id));

        professor.setName(professorDto.getName());

        professorRepository.save(professor);

        return new ProfessorGetDto(professor);
    }

    public void delete(final Integer id){
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", id));
        professorRepository.delete(professor);
    }
}
