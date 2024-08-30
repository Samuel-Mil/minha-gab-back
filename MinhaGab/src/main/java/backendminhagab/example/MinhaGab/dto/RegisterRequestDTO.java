package backendminhagab.example.MinhaGab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String name;
    private String cpf; 
    private String email;
    private String password;
    private String role; 
}
