package backendminhagab.example.MinhaGab.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import backendminhagab.example.MinhaGab.exceptions.GabNotFoundException;
import backendminhagab.example.MinhaGab.models.Gab;
import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.repositories.GabRepository;
import backendminhagab.example.MinhaGab.repositories.GabRequestRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GabService {

    private final GabRepository gabRepository;
    private final GabRequestRepository gabRequestRepository;

    public GabService(GabRepository gabRepository, GabRequestRepository gabRequestRepository) {
        this.gabRepository = gabRepository;
        this.gabRequestRepository = gabRequestRepository;
    }

    // Obter GABs por status
    public List<Gab> getGabByStatus(StatusGab status) {
        return gabRepository.findByStatus(status);
    }

    // Criar uma nova GabRequest
    public GabRequest createGabRequest(GabRequest gabRequest) {
        return gabRequestRepository.save(gabRequest);
    }

    // Criar um novo GAB a partir de uma GabRequest
    public Gab createGabFromRequest(Gab gab, Integer requestId) {
        GabRequest gabRequest = gabRequestRepository.findById(requestId)
            .orElseThrow(() -> new GabNotFoundException("GabRequest not found with ID: " + requestId));

        gab.setGabRequest(gabRequest); // Associar GAB à GabRequest
        return gabRepository.save(gab);
    }

    // Buscar GABs por requestId
    public List<Gab> getGabsByRequestId(Integer requestId) {
        return gabRepository.findByGabRequest_Id(requestId);
    }

    // Buscar GAB por ID
    public Optional<Gab> getGabById(Integer id) {
        return gabRepository.findById(id);
    }

    // Atualizar um GAB existente
    public Gab updateGab(Gab gab) {
        if (!gabRepository.existsById(gab.getId())) {
            throw new GabNotFoundException("GAB not found with ID: " + gab.getId());
        }
        return gabRepository.save(gab);
    }

    // Deletar um GAB por ID
    public void deleteGabById(Integer id) {
        if (!gabRepository.existsById(id)) {
            throw new GabNotFoundException("GAB not found with ID: " + id);
        }
        gabRepository.deleteById(id);
    }

    // Upload de PDF para o campo pdf_file
    @SuppressWarnings("null")
    public Gab uploadPdfFile(Integer gabId, MultipartFile file) throws IOException {
        Gab gab = gabRepository.findById(gabId)
                .orElseThrow(() -> new GabNotFoundException("GAB not found with ID: " + gabId));

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload an empty file.");
        }

        // Verifica se o arquivo é PDF
        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed.");
        }

        gab.setPdfFile(file.getBytes());
        return gabRepository.save(gab);
    }
}
