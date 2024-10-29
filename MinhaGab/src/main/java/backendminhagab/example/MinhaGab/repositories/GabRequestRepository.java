package backendminhagab.example.MinhaGab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backendminhagab.example.MinhaGab.models.GabRequest;
import java.util.List;


public interface GabRequestRepository extends JpaRepository<GabRequest, Integer> {

    List<GabRequest> findByGabRequestId(Integer id);

    List <GabRequest> findByUserId(Integer id);
}
