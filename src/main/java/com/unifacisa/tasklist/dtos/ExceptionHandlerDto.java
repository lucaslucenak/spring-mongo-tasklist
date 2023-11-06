package com.unifacisa.tasklist.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionHandlerDto<VE> {

    private Map<String, String> errors;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
    private List<? extends VE> validExamples;
}