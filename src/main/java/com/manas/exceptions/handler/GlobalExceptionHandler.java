package com.manas.exceptions.handler;

import com.manas.dto.response.ExceptionResponse;
import com.manas.exceptions.AlreadyExistException;
import com.manas.exceptions.BadRequestException;
import com.manas.exceptions.ForbiddenException;
import com.manas.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handlerAlreadyExist(AlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.CONFLICT,
                e.getMessage(),
                AlreadyExistException.class.getSimpleName());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerBadRequest(BadRequestException e){
        return new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                BadRequestException.class.getSimpleName()
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handlerForbidden(ForbiddenException e) {
        return new ExceptionResponse(HttpStatus.FORBIDDEN,
                e.getMessage(),
                ForbiddenException.class.getSimpleName());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerForbidden(NotFoundException e) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                NotFoundException.class.getSimpleName());
    }
}
