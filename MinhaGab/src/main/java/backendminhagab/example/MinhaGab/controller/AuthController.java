package backendminhagab.example.MinhaGab.controller;

import backendminhagab.example.MinhaGab.dto.LoginRequestDTO;
import backendminhagab.example.MinhaGab.dto.RegisterRequestDTO;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.repositories.UserRepository;
import backendminhagab.example.MinhaGab.security.TokenService;
import backendminhagab.example.MinhaGab.Enums.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public String register(@RequestBody RegisterRequestDTO registerDTO) {
        UserModel user = new UserModel();
        user.setName(registerDTO.getName());
        user.setCpf(registerDTO.getCpf());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(Role.valueOf(registerDTO.getRole().toUpperCase()));

        userRepository.save(user);
        return "Usuário registrado com sucesso!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginDTO) {
        UserModel user = userRepository.findByCpf(loginDTO.getCpf())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.getRole().name().equalsIgnoreCase(loginDTO.getRole())) {
            return "Role mismatch!";
        }

        String token = tokenService.generateToken(user);

        return "Bearer " + token;
    }
}
