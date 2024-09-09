package backendminhagab.example.MinhaGab.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotNull(message = "O campo CPF/CNPJ é obrigatório.")
    @Size(min = 11, max = 14, message = "O CPF deve ter 11 dígitos e o CNPJ 14 dígitos.")
    private String cpfcnpj;
    
  

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 10, max = 11, message = "O telefone deve ter entre 10 e 11 dígitos, incluindo o DDD.")
    private String phone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    @NotBlank(message = "O cargo deve ser obrigatório")
    private String role;
}
