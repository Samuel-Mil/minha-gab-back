package backendminhagab.example.MinhaGab.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backendminhagab.example.MinhaGab.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    //Comentado pois estava dando erro, já que aqui pra mim está retornando forbidden ao tentar criar algum comentário
    // List<Answer> findByCommentId(Long commentId); 
}
