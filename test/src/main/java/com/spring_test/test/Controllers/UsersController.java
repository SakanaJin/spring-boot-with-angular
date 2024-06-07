package com.spring_test.test.Controllers;

import com.spring_test.test.Entities.Dtos.UserDto;
import com.spring_test.test.Entities.User;
import com.spring_test.test.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable final Integer id){
        return userService.get(id);
    }

    @PutMapping
    public User create(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody UserDto userDto, @PathVariable final Integer id){
        return  userService.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Integer id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
