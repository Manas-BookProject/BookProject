package com.manas.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JwtUtil {

    private final String SECRET_KEY = "muslima";

    public String generateToken(UserDetails userDetails){
        return JWT.create()
                .withClaim("username", userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusDays(6L).toInstant()))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateTokenAndRetrieveClaim(String token){
        JWTVerifier jwtVerifier = JWT
                .require(Algorithm.HMAC256(SECRET_KEY))
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("username").asString();
    }
}
