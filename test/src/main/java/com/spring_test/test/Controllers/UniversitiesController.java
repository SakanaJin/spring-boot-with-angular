package com.spring_test.test.Controllers;

import com.spring_test.test.Entities.Dtos.GetDtos.UniversityGetDto;
import com.spring_test.test.Entities.Dtos.UniversityDto;
import com.spring_test.test.Entities.University;
import com.spring_test.test.Services.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/universities")
public class UniversitiesController {
    private final UniversityService universityService;

    public UniversitiesController(UniversityService universityService){
        this.universityService = universityService;
    }

    @GetMapping
    public List<UniversityGetDto> getAll(){
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public UniversityGetDto get(final Integer id){
        return universityService.get(id);
    }

    @PostMapping
    public ResponseEntity<UniversityGetDto> create(@RequestBody UniversityDto universityDto){
        return new ResponseEntity<UniversityGetDto>(universityService.create(universityDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityGetDto> update(@RequestBody UniversityDto universityDto, @PathVariable final Integer id){
        return new ResponseEntity<UniversityGetDto>(universityService.update(universityDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Integer id){
        universityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
