package backendminhagab.example.MinhaGab.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backendminhagab.example.MinhaGab.models.Comentarios;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.repositories.CommentRepository;
import backendminhagab.example.MinhaGab.repositories.UserRepository;
import backendminhagab.example.MinhaGab.dto.ComentariosRequestDTO; 

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public Comentarios saveComment(Comentarios comments) {
        return commentRepository.save(comments);
    }

    public List<Comentarios> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comentarios> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public Comentarios updateComment(Long id, ComentariosRequestDTO requestDTO) {
        return commentRepository.findById(id).map(comment -> {
            comment.setComentarios(requestDTO.getComentario());
            comment.setStatus(requestDTO.getStatus());
            UserModel user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + requestDTO.getUserId()));
            comment.setUser(user);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Comentário não encontrado com ID: " + id));
    }

    public List<Comentarios> getCommentsByUserId(Integer userId) {
        return commentRepository.findByUserId(userId);
    }
}
