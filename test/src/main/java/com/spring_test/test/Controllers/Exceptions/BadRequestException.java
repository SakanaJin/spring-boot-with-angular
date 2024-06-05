package com.spring_test.test.Controllers.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String description;

    public BadRequestException(String description){
        super(description);
        this.description = description;
    }
}
