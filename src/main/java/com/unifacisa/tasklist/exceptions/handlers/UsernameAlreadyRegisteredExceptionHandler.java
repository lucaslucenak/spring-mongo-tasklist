package com.unifacisa.tasklist.exceptions.handlers;

import com.unifacisa.tasklist.dtos.ExceptionHandlerDto;
import com.unifacisa.tasklist.exceptions.EmailAlreadyRegisteredException;
import com.unifacisa.tasklist.exceptions.UsernameAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class UsernameAlreadyRegisteredExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(UsernameAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionHandlerDto<?>> handle(UsernameAlreadyRegisteredException ex) {
        ExceptionHandlerDto<String> handlerDto = new ExceptionHandlerDto<>();

        Map<String, String> errors = new HashMap<>();
        errors.put("status", ex.getMessage());

        handlerDto.setErrors(errors);
        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        handlerDto.setLocalDateTime(LocalDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.badRequest().body(handlerDto);
    }
}