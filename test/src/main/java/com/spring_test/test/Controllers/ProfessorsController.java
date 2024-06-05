package com.spring_test.test.Controllers;

import com.spring_test.test.Entities.Dtos.GetDtos.ProfessorGetDto;
import com.spring_test.test.Entities.Dtos.ProfessorDto;
import com.spring_test.test.Entities.Professor;
import com.spring_test.test.Services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professors")
public class ProfessorsController {

    public final ProfessorService professorService;

    public ProfessorsController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping
    public List<ProfessorGetDto> getAll(){
        return professorService.getAll();
    }

    @GetMapping("/{id}")
    public ProfessorGetDto get(@PathVariable final Integer id){
        return professorService.get(id);
    }

    @PostMapping
    public ResponseEntity<ProfessorGetDto> create(@RequestBody ProfessorDto professorDto){
        return new ResponseEntity<ProfessorGetDto>(professorService.create(professorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorGetDto> update(@RequestBody ProfessorDto professorDto, @PathVariable final Integer id){
        return new ResponseEntity<ProfessorGetDto>(professorService.update(professorDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Integer id){
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
