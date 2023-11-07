package com.unifacisa.tasklist.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    @NotNull(message = "Field username shouldn't be null")
    @NotEmpty(message = "Field username shouldn't be empty")
    @NotBlank(message = "Field username shouldn't be blank")
    private String username;
    @NotNull(message = "Field password shouldn't be null")
    @NotEmpty(message = "Field password shouldn't be empty")
    @NotBlank(message = "Field password shouldn't be blank")
    private String password;
}
