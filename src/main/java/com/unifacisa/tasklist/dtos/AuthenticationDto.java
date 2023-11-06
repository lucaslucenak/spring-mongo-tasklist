package com.unifacisa.tasklist.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String username;
    private String password;
}
