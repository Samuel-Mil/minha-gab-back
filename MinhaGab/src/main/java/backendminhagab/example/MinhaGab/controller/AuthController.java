package backendminhagab.example.MinhaGab.controller;

import backendminhagab.example.MinhaGab.dto.LoginRequestDTO;
import backendminhagab.example.MinhaGab.dto.RegisterRequestDTO;
import backendminhagab.example.MinhaGab.dto.RefreshRequest;
import backendminhagab.example.MinhaGab.dto.TokenResponse;
import backendminhagab.example.MinhaGab.exceptions.CampoObrigatorioException;
import backendminhagab.example.MinhaGab.exceptions.UsuarioJaExisteException;
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

import java.util.Map;
import java.util.HashMap;

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
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequestDTO registerDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            // Retorna erros de validação em JSON
            Map<String, String> erros = new HashMap<>();
            result.getFieldErrors()
                    .forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(erros);
        }

        if (userRepository.findByCpfcnpj(registerDTO.getCpfcnpj()).isPresent()) {
            throw new UsuarioJaExisteException("Usuário já existe com este CPF/CNPJ");
        }

        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new UsuarioJaExisteException("Usuário já existe com este e-mail");
        }

        Role role;
        try {
            role = Role.valueOf(registerDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CampoObrigatorioException("Role inválida");
        }

        UserModel user = new UserModel();
        user.setName(registerDTO.getName());
        user.setCpfcnpj(registerDTO.getCpfcnpj());
        user.setPhone(registerDTO.getPhone());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        // Retorna uma resposta de sucesso em JSON
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("mensagem", "Usuário registrado com sucesso!");
        return ResponseEntity.ok(successResponse);
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
                TokenResponse tokenResponse = new TokenResponse(newAccessToken, newRefreshToken,
                        "Refresh bem-sucedido");
                return ResponseEntity.ok(tokenResponse);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new TokenResponse(null, null, "Token de refresh inválido ou expirado"));
    }
}
