package backendminhagab.example.MinhaGab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import backendminhagab.example.MinhaGab.models.Gab;
import backendminhagab.example.MinhaGab.repositories.GabRepository;
import java.util.List;
@Service
public class GabService {

    @Autowired
    private GabRepository gabRepository;

    public List<Gab> getGabByStatus(StatusGab status){
        return gabRepository.findByStatus(status);
    }

    public Gab createGabs(Gab gabs) {
        return gabRepository.save(gabs);
    }

    public List<Gab> getGabsByRequestId(Integer requestId) {
        return gabRepository.findByGabRequest_Id(requestId);
    }


}
