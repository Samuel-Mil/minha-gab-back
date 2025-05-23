package backendminhagab.example.MinhaGab.controller;

import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.services.GabRequestService;
import backendminhagab.example.MinhaGab.services.GabService;
import backendminhagab.example.MinhaGab.models.Gab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/gab_requests")
public class GabRequestController {

    @Autowired
    private final GabRequestService gabRequestService;

    @Autowired
    private GabService gabService;

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
    public ResponseEntity<?> uploadGabRequest(
            @RequestParam("userId") Integer userId, // ID do usuário
            @RequestParam("pdf_file") MultipartFile file) {

        try {
            // Verifica se o arquivo PDF foi enviado
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("O arquivo 'pdf_file' não foi enviado.");
            }

            // Cria uma nova GabRequest e define o usuário
            GabRequest newRequest = new GabRequest();
            UserModel user = new UserModel();
            user.setId(userId);
            newRequest.setUser(user);

            // Salva a GabRequest e o arquivo PDF
            GabRequest savedRequest = gabRequestService.createGabRequestWithFile(newRequest, file);
            return ResponseEntity.ok("GabRequest criada com sucesso: " + savedRequest.getId());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar o upload: " + e.getMessage());
        }
    }

    //nao sei se e o certo de fato, mas irei deixar o endpoint para achar a gab relacionada ao nome/cpf aqui
    @GetMapping("/search")
    public ResponseEntity<List<Gab>> gabsByCpfOrName(@RequestParam String cpfOrName) {
        List<Gab> gabs = gabService.getGabsCpfOrName(cpfOrName);
        return ResponseEntity.ok(gabs);
    }

}
