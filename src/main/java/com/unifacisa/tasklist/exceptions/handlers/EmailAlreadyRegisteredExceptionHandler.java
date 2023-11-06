package com.unifacisa.tasklist.exceptions.handlers;

import com.unifacisa.tasklist.dtos.ExceptionHandlerDto;
import com.unifacisa.tasklist.exceptions.ResourceNotFoundException;
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
public class EmailAlreadyRegisteredExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDto<?>> handle(ResourceNotFoundException ex) {
        ExceptionHandlerDto<String> handlerDto = new ExceptionHandlerDto<>();

        Map<String, String> errors = new HashMap<>();
        errors.put("status", ex.getMessage());

        handlerDto.setErrors(errors);
        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        handlerDto.setLocalDateTime(LocalDateTime.now(ZoneId.of("Z")));

        handlerDto.setValidExamples(List.of("email.email@email.com", "email.123@email.com.br", "email.122312@email.edu.com.br"));

        return ResponseEntity.badRequest().body(handlerDto);
    }
}