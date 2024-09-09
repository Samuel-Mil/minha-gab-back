package backendminhagab.example.MinhaGab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDTO {
    private String cpfcnpj; 
    private String password;
    private String role; 
}
