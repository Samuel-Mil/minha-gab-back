package backendminhagab.example.MinhaGab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import backendminhagab.example.MinhaGab.models.Gab;

public interface GabRepository extends JpaRepository<Gab, Integer> {
    
    // Buscar GABs por status
    List<Gab> findByStatus(StatusGab status);

    // Buscar GABs por requestId
    List<Gab> findByGabRequest_Id(Integer requestId);
}
