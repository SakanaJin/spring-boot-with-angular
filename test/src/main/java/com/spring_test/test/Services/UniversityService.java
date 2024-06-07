package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Entities.Course;
import com.spring_test.test.Entities.Dtos.UniversityDto;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    public List<University> getAll(){
        return universityRepository.findAll();
    }

    public University get(final Integer id){
        return universityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University", "Id", id));
    }

    public University create(UniversityDto universityDto){
        if(universityDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        var university = University.builder()
                .Name(universityDto.getName())
                .Courses(new ArrayList<Course>())
                .build();

        return universityRepository.save(university);
    }

    public University update(UniversityDto universityDto, final Integer id){
        if(universityDto.getName().isEmpty()){
            throw new BadRequestException("name must not be empty");
        }

        University university = universityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University", "Id", id));

        university.setName(universityDto.getName());

        return universityRepository.save(university);
    }

    public void delete(final Integer id){
        if(id == 0){
            throw new BadRequestException("id must not be empty");
        }
        University university = universityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University", "Id", id));
        universityRepository.delete(university);
    }
}
