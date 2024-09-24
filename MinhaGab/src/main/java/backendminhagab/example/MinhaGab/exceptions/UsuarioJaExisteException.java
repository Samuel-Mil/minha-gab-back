package backendminhagab.example.MinhaGab.exceptions;

public class UsuarioJaExisteException extends RuntimeException {
    public UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }
}
