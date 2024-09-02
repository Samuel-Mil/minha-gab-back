package backendminhagab.example.MinhaGab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String cpf; 
    private String password;
    private String role; 
}
