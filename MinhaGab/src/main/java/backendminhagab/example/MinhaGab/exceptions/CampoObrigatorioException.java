package backendminhagab.example.MinhaGab.exceptions;

public class CampoObrigatorioException extends RuntimeException {
    public CampoObrigatorioException(String mensagem) {
        super(mensagem);
    }
}