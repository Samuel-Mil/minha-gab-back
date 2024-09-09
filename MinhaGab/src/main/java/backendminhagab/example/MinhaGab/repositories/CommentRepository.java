package backendminhagab.example.MinhaGab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backendminhagab.example.MinhaGab.models.Comentarios;

public interface CommentRepository extends JpaRepository<Comentarios, Long> {

    List<Comentarios> findByUserId(Integer userId); 
}
