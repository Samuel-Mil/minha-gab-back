package backendminhagab.example.MinhaGab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import backendminhagab.example.MinhaGab.models.Gab;
import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.repositories.GabRepository;
import backendminhagab.example.MinhaGab.repositories.GabRequestRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GabService {

    @Autowired
    private GabRepository gabRepository;

    @Autowired
    private GabRequestRepository gabRequestRepository; // Repositório para GabRequest

    // Obter GABs por status
    public List<Gab> getGabByStatus(StatusGab status) {
        return gabRepository.findByStatus(status);
    }

    // Criar uma nova GabRequest
    public GabRequest createGabRequest(GabRequest gabRequest) {
        return gabRequestRepository.save(gabRequest);
    }

    // Criar um novo GAB a partir de uma GabRequest
    public Gab createGabsFromRequest(Gab gab, Integer requestId) {
        GabRequest gabRequest = gabRequestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("GabRequest not found with ID: " + requestId));
        
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
        // Verificar se o GAB existe antes de atualizar
        if (!gabRepository.existsById(gab.getId())) {
            throw new RuntimeException("GAB not found with ID: " + gab.getId());
        }
        return gabRepository.save(gab);
    }

    // Deletar um GAB por ID
    public void deleteGabById(Integer id) {
        if (!gabRepository.existsById(id)) {
            throw new RuntimeException("GAB not found with ID: " + id);
        }
        gabRepository.deleteById(id);
    }

    // Upload de PDF para o campo pdf_file
    public Gab uploadPdfFile(Integer gabId, MultipartFile file) throws IOException {
        Optional<Gab> gabOptional = gabRepository.findById(gabId);
        if (gabOptional.isPresent()) {
            Gab gab = gabOptional.get();
            if (file.isEmpty()) {
                throw new RuntimeException("Cannot upload an empty file.");
            }
            gab.setPdfFile(file.getBytes());
            return gabRepository.save(gab);
        } else {
            throw new RuntimeException("GAB not found with ID: " + gabId);
        }
    }
}
