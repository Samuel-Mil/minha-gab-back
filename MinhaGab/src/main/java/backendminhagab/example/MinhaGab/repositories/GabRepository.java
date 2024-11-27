package backendminhagab.example.MinhaGab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import backendminhagab.example.MinhaGab.models.Gab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GabRepository extends JpaRepository<Gab, Integer> {
    
    // Buscar GABs por status
    List<Gab> findByStatus(StatusGab status);

    // Buscar GABs por requestId
    List<Gab> findByGabRequest_Id(Integer requestId);

    // Buscar GABs passando nome ou cpf do usuario, ate o momento minha logica foi esta da query
    @Query("SELECT g FROM Gab g WHERE g.user.cpfcnpj = :cpfOrName OR g.user.name LIKE %:cpfOrName%")
    List<Gab> findByCpfOrName(@Param("cpfOrName") String cpfOrName);
}
