package backendminhagab.example.MinhaGab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backendminhagab.example.MinhaGab.models.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long>{
    //adicionar coisas a mais se necessário
}
