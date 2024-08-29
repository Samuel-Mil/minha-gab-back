package backendminhagab.example.MinhaGab.controller;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendminhagab.example.MinhaGab.dto.LoginRequestDTO;
import backendminhagab.example.MinhaGab.dto.RegisterRequestDTO;
import backendminhagab.example.MinhaGab.dto.ResponseDTO;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.repositories.UserRepository;
import backendminhagab.example.MinhaGab.security.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        UserModel user = this.repository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("User Not Found"));
        if (passwordEncoder.matches(body.password() ,user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<UserModel> user = this.repository.findByEmail(body.email());
        if (user.isEmpty()) {
            UserModel newUser = new UserModel(); 
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
            
        }

        return ResponseEntity.badRequest().build();
    }

    /*nome cpf email senha  */
}
