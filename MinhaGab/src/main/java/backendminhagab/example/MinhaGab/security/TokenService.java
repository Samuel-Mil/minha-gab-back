package backendminhagab.example.MinhaGab.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    private final UserRepository userRepository;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(UserModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("MinhaGab")
                    .withSubject(user.getCpf()) // Usando CPF como subject
                    .withExpiresAt(generateAccessExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao criar o token JWT", e);
        }
    }

    public String generateRefreshToken(UserModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("MinhaGab")
                    .withSubject(user.getCpf()) // Usando CPF como subject
                    .withExpiresAt(generateRefreshExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao criar o token de refresh", e);
        }
    }

    public String validateToken(String token, boolean isRefreshToken) {
        if (token == null || token.isEmpty()) {
            logger.warn("Token é nulo ou vazio");
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("MinhaGab")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            String subject = decodedJWT.getSubject();
            logger.info("Token verificado com sucesso, subject: {}", subject);
            return subject;
        } catch (JWTVerificationException e) {
            logger.error("Falha na verificação do token: {}", e.getMessage());
            return null;
        }
    }

    @Transactional
    public void updateRefreshToken(String cpf, String refreshToken) {
        userRepository.updateRefreshToken(cpf, refreshToken);
    }

    private Instant generateAccessExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC); // Access token expira em 2 horas
    }

    private Instant generateRefreshExpirationDate() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.UTC); // Refresh token expira em 30 dias
    }
}
