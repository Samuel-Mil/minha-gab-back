package backendminhagab.example.MinhaGab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendminhagab.example.MinhaGab.dto.AnswerRequestDTO;
import backendminhagab.example.MinhaGab.models.Answer;
import backendminhagab.example.MinhaGab.models.Comentarios;
import backendminhagab.example.MinhaGab.services.AnswerService;
import backendminhagab.example.MinhaGab.services.CommentService;

@RestController
@RequestMapping("/respostas")
public class AnswerController {

    // criar lógica do controller, não criei nada pois aq está retornando forbidden,
    // não sei se é só no meu, mas falta criar o método Post

    @Autowired
    private AnswerService answerService;

    @Autowired
    private CommentService commentService;
    

    //apenas peguei de base a lógica de criar comentário, provável q terá q ter atualizações
    
    //Não irá funcionar justamente pelo motivo de, ao tentar criar algum comentário, o meu retorna um forbidden, e nesse caso precisa do ID do comentário
    @PostMapping("/criar")
    public ResponseEntity<Answer> criarResposta(@RequestBody AnswerRequestDTO requestDTO) {
        Comentarios comentario = commentService.getCommentById(requestDTO.getCommentId())
            .orElseThrow(() -> new RuntimeException("Comentário não encontrado com ID: " + requestDTO.getCommentId()));

        Answer answer = new Answer();
        answer.setAnswer(requestDTO.getAnswer());
        answer.setAnswer(requestDTO.getAnswer());
        answer.setComentarios(comentario);

        Answer answerSave = answerService.saveAnswer(answer);
        return new ResponseEntity<>(answerSave, HttpStatus.CREATED);
    }
    
    @GetMapping("/todas")
    public List<Answer> obterTodasRespostas() {
        return answerService.getAllAnswer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> obterRespostaPorId(@PathVariable Integer id) {
        return answerService.getAnswerById(id)
                .map(answer -> new ResponseEntity<>(answer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> atualizarResposta(@PathVariable Integer id,
            @RequestBody AnswerRequestDTO requestDTO) {
        Answer answerAtt = answerService.updateAnswer(id, requestDTO);
        return new ResponseEntity<>(answerAtt, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Integer id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Comentádo pois estava dando erro, já que aqui pra mim está retornando forbidden ao tentar criar algum comentário
    
    // @GetMapping("/comentario/{commentId}")
    // public ResponseEntity<List<Answer>> obterComentariosPorIdComentario(@PathVariable Long commentId) {
    //     List<Answer> answers = answerService.getAnswerByCommentId(commentId);
    //     if (answers.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }
    //     return new ResponseEntity<>(answers, HttpStatus.OK);
    // }
}
