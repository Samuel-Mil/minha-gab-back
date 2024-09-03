package backendminhagab.example.MinhaGab.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backendminhagab.example.MinhaGab.models.Comentarios;
import backendminhagab.example.MinhaGab.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private  CommentRepository repository;

    @Autowired
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.repository = commentRepository;
    }

    public Comentarios saveComment(Comentarios comments) {
        return repository.save(comments);
    }

    public List<Comentarios> getAllComments() {
        return repository.findAll();
    }

    public Optional<Comentarios> getCommentById(Long id) {
        return repository.findById(id);
    }

    public void deleteComment(Long id) {
        repository.deleteById(id);
    }

    public Comentarios updComments(Long id, Comentarios newComments) {
        return repository.findById(id).map(comments -> {
            comments.setComentarios(newComments.getComentarios());
            comments.setStatus(newComments.getStatus());
            return repository.save(comments);
        }).orElseThrow(() -> new RuntimeException("Comment not found with Id " + id)); //fazer tratamento de exception personalizado
    }

    public List<Comentarios> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

}
