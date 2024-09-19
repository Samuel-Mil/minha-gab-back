package backendminhagab.example.MinhaGab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@ToString
public class LoginRequestDTO {

    @NotBlank(message = "O CPF/CNPJ é obrigatório")
    @Size(min = 11, max = 14, message = "O CPF deve ter 11 dígitos e o CNPJ 14 dígitos")
    private String cpfcnpj;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    @NotBlank(message = "O role é obrigatório")
    private String role;
}
