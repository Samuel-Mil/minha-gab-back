package backendminhagab.example.MinhaGab.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backendminhagab.example.MinhaGab.models.Comments;
import backendminhagab.example.MinhaGab.services.CommentService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    // método para criar comentário
    @PostMapping("/criarcomentarios")
    public ResponseEntity<Comments> createComment(@RequestBody Comments comments) {
        Comments savedComments = commentService.saveComment(comments);
        return new ResponseEntity<>(savedComments, HttpStatus.CREATED);
    }

    // buscar todos os comentários
    @GetMapping("/todoscomentarios")
    public List<Comments> getAllComments() {
        return commentService.getAllComments();
    }

    // buscar comentário por ID
    @GetMapping("/comentario/{id}")
    public ResponseEntity<Comments> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id).map(comments -> new ResponseEntity<>(comments, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // método para atualizar (update) comentário
    @PutMapping("/mudarcomentario/{id}")
    public ResponseEntity<Comments> updEntity(@PathVariable Long id, @RequestBody Comments comments) {
        return new ResponseEntity<>(commentService.updComments(id, comments), HttpStatus.OK);
    }

    // método para deletar comentário por ID
    @DeleteMapping("/deletarcomentario/{id}")
    public ResponseEntity<Comments> dEntity(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
