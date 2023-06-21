package com.bridgeLabz.userFitnessGoals.exception;

import com.bridgeLabz.userFitnessGoals.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> argHandler(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        List<String> list = errors.stream().map(object -> object.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto dto = new ResponseDto("Error messages", list);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseDto> userId(UserException exception) {
        ResponseDto dto = new ResponseDto("User id not found", exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FitnessException.class)
    public ResponseEntity<ResponseDto> fitnessId(FitnessException exception) {
        ResponseDto dto = new ResponseDto("Fitness Id not found", exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
