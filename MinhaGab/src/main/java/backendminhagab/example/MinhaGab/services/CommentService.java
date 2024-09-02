package backendminhagab.example.MinhaGab.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backendminhagab.example.MinhaGab.models.Comments;
import backendminhagab.example.MinhaGab.repositories.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository repository;

    public CommentService(CommentRepository commentRepository) {
        this.repository = commentRepository;
    }

    public Comments saveComment(Comments comments) {
        return repository.save(comments);
    }

    public List<Comments> getAllComments() {
        return repository.findAll();
    }

    public Optional<Comments> getCommentById(Long id) {
        return repository.findById(id);
    }

    public void deleteComment(Long id) {
        repository.deleteById(id);
    }

    public Comments updComments(Long id, Comments newComments) {
        return repository.findById(id).map(comments -> {
            comments.setComment(newComments.getComment());
            comments.setStatus(newComments.getStatus());
            return repository.save(comments);
        }).orElseThrow(() -> new RuntimeException("Comment not found with Id " + id)); //fazer tratamento de exception personalizado
    }

}
