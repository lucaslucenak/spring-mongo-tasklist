package com.unifacisa.tasklist.controllers;

import com.unifacisa.tasklist.dtos.AuthenticationDto;
import com.unifacisa.tasklist.dtos.JwtAuthenticatedDto;
import com.unifacisa.tasklist.models.UserModel;
import com.unifacisa.tasklist.repositories.UserRepository;
import com.unifacisa.tasklist.services.JwtTokenService;
import com.unifacisa.tasklist.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtAuthenticatedDto> login(@RequestBody @Valid AuthenticationDto authenticationPostDto) {

        var clientAccountPassword = new UsernamePasswordAuthenticationToken(authenticationPostDto.getUsername(), authenticationPostDto.getPassword());
        var auth = authenticationManager.authenticate(clientAccountPassword);

        var jwtToken = jwtTokenService.generateJwtToken(userService.findUserByUsername(authenticationPostDto.getUsername()));

        return ResponseEntity.ok(new JwtAuthenticatedDto(jwtToken));
    }
}
