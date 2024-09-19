package backendminhagab.example.MinhaGab.controller;

import backendminhagab.example.MinhaGab.dto.LoginRequestDTO;
import backendminhagab.example.MinhaGab.dto.RegisterRequestDTO;
import backendminhagab.example.MinhaGab.dto.RefreshRequest;
import backendminhagab.example.MinhaGab.dto.TokenResponse;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.repositories.UserRepository;
import backendminhagab.example.MinhaGab.security.TokenService;
import jakarta.validation.Valid;
import backendminhagab.example.MinhaGab.Enums.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerDTO) {
        if (userRepository.findByCpfcnpj(registerDTO.getCpfcnpj()).isPresent()
                || userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe com este CPF/CNPJ ou e-mail");
        }

        Role role;
        try {
            role = Role.valueOf(registerDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role inválida");
        }

        UserModel user = new UserModel();
        user.setName(registerDTO.getName());
        user.setCpfcnpj(registerDTO.getCpfcnpj());
        user.setPhone(registerDTO.getPhone());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(role);

        userRepository.save(user);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @SuppressWarnings("null")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequestDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest()
                .body(new TokenResponse(null, null, result.getFieldError().getDefaultMessage()));
        }

        UserModel user = userRepository.findByCpfcnpj(loginDTO.getCpfcnpj())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenResponse(null, null, "Credenciais inválidas"));
        }

        if (!user.getRole().name().equalsIgnoreCase(loginDTO.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new TokenResponse(null, null, "Role incompatível"));
        }

        String accessToken = tokenService.generateToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken); // Atualiza o refresh token no banco de dados
        userRepository.save(user);

        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken, "Login bem-sucedido");
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        String refreshToken = refreshRequest.getRefreshToken();
        String cpf = tokenService.validateToken(refreshToken, true);

        if (cpf != null) {
            Optional<UserModel> optionalUser = userRepository.findByCpfcnpj(cpf);
            if (optionalUser.isPresent()) {
                UserModel user = optionalUser.get();
                String newAccessToken = tokenService.generateToken(user);
                String newRefreshToken = tokenService.generateRefreshToken(user);
                tokenService.updateRefreshToken(cpf, newRefreshToken); // Atualiza o refresh token
                TokenResponse tokenResponse = new TokenResponse(newAccessToken, newRefreshToken, "Refresh bem-sucedido");
                return ResponseEntity.ok(tokenResponse);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new TokenResponse(null, null, "Token de refresh inválido ou expirado"));
    }
}
