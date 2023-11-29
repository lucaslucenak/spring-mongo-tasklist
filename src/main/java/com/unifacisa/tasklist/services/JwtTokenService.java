package com.unifacisa.tasklist.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.unifacisa.tasklist.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${jwt.security.secret}")
    private String secret;

    public String generateJwtToken(UserModel userModel) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String jwtToken = JWT.create()
                .withIssuer("auth-api")
                .withSubject(userModel.getUsername())
                .withClaim("role", userModel.getRole().toString())
                .withClaim("userId", userModel.getId())
                .withExpiresAt(this.getExpirationDate())
                .sign(algorithm);

        return jwtToken;
    }

    public String validateJwtToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(jwtToken)
                .getSubject();
    }

    private Date getExpirationDate() {
        return Date.from(Instant.now().plus(Duration.ofDays(1)));
    }
}
