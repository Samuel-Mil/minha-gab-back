package backendminhagab.example.MinhaGab.services;

import backendminhagab.example.MinhaGab.dto.AnswerRequestDTO;
import backendminhagab.example.MinhaGab.models.Answer;
import backendminhagab.example.MinhaGab.models.Comentarios;
import backendminhagab.example.MinhaGab.repositories.AnswerRepository;
import backendminhagab.example.MinhaGab.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAllAnswer() {
        return answerRepository.findAll();
    }

    public Optional<Answer> getAnswerById(Integer id) {
        return answerRepository.findById(id);
    }

    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }

    public Answer updateAnswer(Integer id, AnswerRequestDTO requestDTO) {
        return answerRepository.findById(id).map(answer -> {
            answer.setAnswer(requestDTO.getAnswer());
            Comentarios comentario = commentRepository.findById(requestDTO.getCommentId())
                    .orElseThrow(
                            () -> new RuntimeException("Comentário não encontrado com ID: " + requestDTO.getCommentId()));
            answer.setComentarios(comentario);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new RuntimeException("Resposta não encontrado com ID: " + id));
    }

    //Comentádo pois estava dando erro, já que aqui pra mim está retornando forbidden ao tentar criar algum comentário
    // public List<Answer> getAnswerByCommentId(Long commentId) {
    //     return answerRepository.findByCommentId(commentId);
    // }
}
