package backendminhagab.example.MinhaGab.services;


import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.repositories.GabRequestRepository;

import java.util.List;
import java.util.Optional;

public class GabRequestService {

    private final GabRequestRepository gabRequestRepository;

   
    public GabRequestService(GabRequestRepository gabsRequestRepository) {
        this.gabRequestRepository = gabsRequestRepository;
    }


    public GabRequest createGabsRequest(GabRequest gabsRequest) {
        //adicionar regras de negócios, se necessário
        return gabRequestRepository.save(gabsRequest);
    }

    public void deletarGabRequest(Integer id) {
        Optional<GabRequest> request = gabRequestRepository.findById(id);
        if (request.isPresent()) {
            gabRequestRepository.deleteById(id);
        } else {
            throw new RuntimeException("Solicitação de GAB não encontrada com ID: " + id);
        }
    }

    public List<GabRequest> getAllRequests() {
        return gabRequestRepository.findAll();
    }

}
