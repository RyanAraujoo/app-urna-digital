package com.votemais.urnadigital.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.votemais.urnadigital.domain.Associado;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.secret}")
    private String secret;


    public String CreateJWT(Associado associado) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("jwt-auth")
                    .withSubject(associado.getUsername())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);

        } catch (JWTCreationException excp) {
            throw new RuntimeException("Error with JWT", excp);
        }
    }

    public String VerifyJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("jwt-auth")
                    .build()
                    .verify(token)
                    .getSubject();


        } catch (JWTVerificationException excp) {
            return "";
        }
    }

    public Instant getExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
