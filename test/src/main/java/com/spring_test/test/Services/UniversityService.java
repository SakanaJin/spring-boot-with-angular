package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Dtos.GetDtos.UniversityGetDto;
import com.spring_test.test.Entities.Dtos.UniversityDto;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    public List<UniversityGetDto> getAll(){
        return universityRepository.findAll().stream().map(UniversityGetDto::new).toList();
    }

    public UniversityGetDto get(final Integer id){
        University university = universityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University", "Id", id));
        return new UniversityGetDto(university);
    }

    public UniversityGetDto create(UniversityDto universityDto){
        if(universityDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        var university = University.builder()
                .Name(universityDto.getName())
                .Courses(new ArrayList<Course>())
                .build();

        universityRepository.save(university);

        return new UniversityGetDto(university);
    }

    public UniversityGetDto update(UniversityDto universityDto, final Integer id){
        if(universityDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        University university = universityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University", "Id", id));

        university.setName(universityDto.getName());

        universityRepository.save(university);

        return new UniversityGetDto(university);
    }

    public void delete(final Integer id){
        University university = universityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University", "Id", id));
        universityRepository.delete(university);
    }
}
