package com.spring_test.test.Services;

import com.spring_test.test.Controllers.Exceptions.BadRequestException;
import com.spring_test.test.Controllers.Exceptions.ResourceNotFoundException;
import com.spring_test.test.Entities.Dtos.UserDto;
import com.spring_test.test.Entities.User;
import com.spring_test.test.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User get(final Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    public User create(UserDto userDto){
        if(userDto.getUserName().isEmpty()){
            throw new BadRequestException("username must not be empty");
        }

        if(userDto.getFirstName().isEmpty()){
            throw new BadRequestException("first name must not be empty");
        }

        if(userDto.getLastName().isEmpty()){
            throw new BadRequestException("last name must not be empty");
        }

        if(userDto.getEmail().isEmpty()){
            throw new BadRequestException("email must not be empty");
        }

        if(userDto.getUserName().contains(" ")){
            throw new BadRequestException("username must not contain spaces");
        }

        if(userDto.getFirstName().contains(" ")){
            throw new BadRequestException("first name must not contain spaces");
        }

        if(userDto.getLastName().contains(" ")){
            throw new BadRequestException("last name must not contain spaces");
        }

        if(userDto.getEmail().contains(" ")){
            throw new BadRequestException("email must not contain spaces");
        }

        var user = User.builder()
                .UserName(userDto.getUserName())
                .FirstName(userDto.getFirstName())
                .LastName(userDto.getLastName())
                .Email(userDto.getEmail())
                .Courses(new ArrayList<>())
                .build();

        return userRepository.save(user);
    }

    public User update(UserDto userDto, final Integer id){
        if(id == 0){
            throw new BadRequestException("id must not be empty");
        }

        if(userDto.getUserName().isEmpty()){
            throw new BadRequestException("username must not be empty");
        }

        if(userDto.getFirstName().isEmpty()){
            throw new BadRequestException("first name must not be empty");
        }

        if(userDto.getLastName().isEmpty()){
            throw new BadRequestException("last name must not be empty");
        }

        if(userDto.getEmail().isEmpty()){
            throw new BadRequestException("email must not be empty");
        }

        if(userDto.getUserName().contains(" ")){
            throw new BadRequestException("username must not contain spaces");
        }

        if(userDto.getFirstName().contains(" ")){
            throw new BadRequestException("first name must not contain spaces");
        }

        if(userDto.getLastName().contains(" ")){
            throw new BadRequestException("last name must not contain spaces");
        }

        if(userDto.getEmail().contains(" ")){
            throw new BadRequestException("email must not contain spaces");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }

    public void delete(final Integer id){
        if (id == 0){
            throw new BadRequestException("id must not be empty");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        user.getCourses().forEach(x -> x.getUsers().remove(user));

        userRepository.delete(user);
    }
}
