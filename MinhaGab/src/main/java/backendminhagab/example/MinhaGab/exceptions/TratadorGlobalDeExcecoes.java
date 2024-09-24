package backendminhagab.example.MinhaGab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

import java.util.Map;
import java.util.HashMap;

@RestControllerAdvice
public class TratadorGlobalDeExcecoes extends ResponseEntityExceptionHandler {

    // Tratamento de exceção para usuário já existente
    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<Map<String, String>> tratarUsuarioJaExiste(UsuarioJaExisteException ex) {
        Map<String, String> respostaErro = new HashMap<>();
        respostaErro.put("mensagem", ex.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    // Tratamento de exceção para campos obrigatórios ausentes
    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<Map<String, String>> tratarCampoObrigatorio(CampoObrigatorioException ex) {
        Map<String, String> respostaErro = new HashMap<>();
        respostaErro.put("mensagem", ex.getMessage());
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    /*Método para tratar erros de validação de campos nos métodos das controllers
    Esse método é chamado automaticamente quando algum argumento de uma requisição falha na validação
    Por exemplo, se um campo obrigatório não for fornecido ou se não respeitar as regras de validação definidas*/
     
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> erros = new HashMap<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            String nomeCampo = erro.getField();
            String mensagemErro = erro.getDefaultMessage();
            erros.put(nomeCampo, mensagemErro);
        }

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }
}
