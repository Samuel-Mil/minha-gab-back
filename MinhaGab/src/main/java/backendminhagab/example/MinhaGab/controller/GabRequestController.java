package backendminhagab.example.MinhaGab.controller;

import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.services.GabRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/gab_requests")
public class GabRequestController {

    private final GabRequestService gabRequestService;

    public GabRequestController(GabRequestService gabRequestService) {
        this.gabRequestService = gabRequestService;
    }

    // Endpoint para criar uma nova GabRequest
    @PostMapping("/create")
    public ResponseEntity<GabRequest> createGabRequest(@RequestBody GabRequest gabRequest) {
        GabRequest savedRequest = gabRequestService.createGabRequest(gabRequest);
        return ResponseEntity.ok(savedRequest); // Retorna a GabRequest salva
    }

    // Endpoint para fazer upload de um PDF associado à GabRequest
    @PostMapping("/upload")
    public ResponseEntity<String> uploadGabRequest(
            @RequestParam("userId") Integer userId, // ID do usuário
            @RequestParam("pdf_file") MultipartFile file) {

        // Verifica se o arquivo PDF foi enviado
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("O arquivo 'pdf_file' não foi enviado.");
        }

        // Cria uma nova GabRequest
        GabRequest newRequest = new GabRequest();

        // Atribui o usuário à GabRequest
        UserModel user = new UserModel();
        user.setId(userId); // Define o ID do usuário
        newRequest.setUser(user); // Associa o usuário à GabRequest

        // Salva a GabRequest e o arquivo PDF
        GabRequest savedRequest = gabRequestService.createGabRequestWithFile(newRequest, file);
        
        return ResponseEntity.ok("GabRequest criada com sucesso: " + savedRequest.getId()); // Retorna confirmação com ID da GabRequest salva
    }

    // Outros métodos existentes (por exemplo, para listar requisições, deletar, etc.)
}
