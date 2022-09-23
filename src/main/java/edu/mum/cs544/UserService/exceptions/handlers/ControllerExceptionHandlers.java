package edu.mum.cs544.UserService.exceptions.handlers;

import edu.mum.cs544.UserService.dtos.ResponseDto;
import edu.mum.cs544.UserService.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<String>> exception(IllegalArgumentException exception) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, true, null, exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<String>> exception(UsernameAlreadyExistsException exception) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, true, null, exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }
}
