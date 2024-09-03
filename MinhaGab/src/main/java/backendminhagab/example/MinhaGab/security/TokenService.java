package backendminhagab.example.MinhaGab.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.JWTCreationException;

import backendminhagab.example.MinhaGab.models.UserModel;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                .withIssuer("MinhaGab")
                .withSubject(user.getCpf())  // Alterado para CPF, se necessário
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);   
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while creating JWT token", e);
        }
    }

    public String validateToken(String token){
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("MinhaGab")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);  // Alterado para UTC, ajuste conforme necessário
    }
}
