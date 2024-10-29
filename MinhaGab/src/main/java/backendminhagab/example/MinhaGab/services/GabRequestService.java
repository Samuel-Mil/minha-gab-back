package backendminhagab.example.MinhaGab.services;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import backendminhagab.example.MinhaGab.models.Gab;
import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.repositories.GabRepository;
import backendminhagab.example.MinhaGab.repositories.GabRequestRepository;
import backendminhagab.example.MinhaGab.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class GabRequestService {

    private final GabRequestRepository gabRequestRepository;
    private final GabRepository gabRepository;
    private final UserRepository userRepository;

    public GabRequestService(GabRequestRepository gabRequestRepository, GabRepository gabRepository, UserRepository userRepository) {
        this.gabRequestRepository = gabRequestRepository;
        this.gabRepository = gabRepository;
        this.userRepository = userRepository;
    }

    public GabRequest createGabRequest(GabRequest gabRequest) {
        // Verificar se o usuário associado não é nulo
        if (gabRequest.getUser() == null) {
            throw new RuntimeException("Usuário deve ser fornecido na solicitação de GAB.");
        }
        
        // Adicionar regras de negócios, se necessário
        return gabRequestRepository.save(gabRequest);
    }

    // Método para criar uma GabRequest com upload de PDF
    public GabRequest createGabRequestWithFile(GabRequest gabRequest, MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("O arquivo PDF não pode ser vazio.");
        }

        try {
            // Verifica se o usuário associado é válido
            UserModel user = userRepository.findById(gabRequest.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            gabRequest.setUser(user); // Associa o usuário à GabRequest

            // Salva a GabRequest
            GabRequest savedRequest = gabRequestRepository.save(gabRequest);

            // Cria a nova Gab associada à GabRequest
            Gab gab = new Gab();
            gab.setGabRequest(savedRequest); // Associa a Gab ao GabRequest
            gab.setUser(user); // Associa o usuário à Gab
            gab.setPdfFile(file.getBytes()); // Converte e armazena o PDF em bytes
            gab.setStatus(StatusGab.PENDENTE); // Define o status inicial conforme sua lógica

            // Salva a Gab no banco de dados
            gabRepository.save(gab);

            return savedRequest; // Retorna a GabRequest salva
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar o arquivo PDF", e);
        }
    }

    public void deleteGabRequest(Integer id) {
        if (!gabRequestRepository.existsById(id)) {
            throw new RuntimeException("Solicitação de GAB não encontrada com ID: " + id);
        }
        gabRequestRepository.deleteById(id);
    }

    public List<GabRequest> getAllRequests() {
        return gabRequestRepository.findAll();
    }
}
