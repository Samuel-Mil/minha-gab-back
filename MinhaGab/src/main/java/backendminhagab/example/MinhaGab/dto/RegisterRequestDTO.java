package backendminhagab.example.MinhaGab.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, message = "CPF deve ter no mínimo 11 caracteres")
    private String cpf;
    
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 14, message = "CNPJ deve ter no mínimo 14 caracteres")
    private String cnpj;

    @NotBlank(message = "Telefone é obrigatório")
    private String phone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    @NotBlank(message = "Role é obrigatória")
    private String role;
}
