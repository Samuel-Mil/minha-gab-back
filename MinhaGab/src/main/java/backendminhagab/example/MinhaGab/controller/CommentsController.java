package backendminhagab.example.MinhaGab.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backendminhagab.example.MinhaGab.models.Comentarios;
import backendminhagab.example.MinhaGab.models.UserModel;
import backendminhagab.example.MinhaGab.services.CommentService;
import backendminhagab.example.MinhaGab.services.UserService;
import backendminhagab.example.MinhaGab.dto.ComentariosRequestDTO;  // Atualize a importação

@RestController
@RequestMapping("/comentarios")
public class CommentsController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentsController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Comentarios> criarComentario(@RequestBody ComentariosRequestDTO requestDTO) {
        UserModel usuario = userService.getUserById(requestDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + requestDTO.getUserId()));

        Comentarios comentario = new Comentarios();
        comentario.setComentarios(requestDTO.getComentario());
        comentario.setStatus(requestDTO.getStatus());
        comentario.setUser(usuario);

        Comentarios comentarioSalvo = commentService.saveComment(comentario);
        return new ResponseEntity<>(comentarioSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public List<Comentarios> obterTodosComentarios() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentarios> obterComentarioPorId(@PathVariable Long id) {
        return commentService.getCommentById(id)
            .map(comentario -> new ResponseEntity<>(comentario, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentarios> atualizarComentario(@PathVariable Long id, @RequestBody ComentariosRequestDTO requestDTO) {
        Comentarios comentarioAtualizado = commentService.updateComment(id, requestDTO);
        return new ResponseEntity<>(comentarioAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Comentarios>> obterComentariosPorIdUsuario(@PathVariable Integer userId) {
        List<Comentarios> comentarios = commentService.getCommentsByUserId(userId);
        if (comentarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }
}
