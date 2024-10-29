package backendminhagab.example.MinhaGab.services;


import backendminhagab.example.MinhaGab.models.Comentarios;
import backendminhagab.example.MinhaGab.models.RespostaComentario;
import backendminhagab.example.MinhaGab.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RespostaService {

    @Autowired
    private final CommentRepository commentRepository;

    public RespostaService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public RespostaComentario adicionarResposta(Long comentarioId, String respostaConteudo) {
        // Encontrar o comentário
        Comentarios comentario = commentRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
    
        // Criar a resposta
        RespostaComentario resposta = new RespostaComentario();
        resposta.setConteudo(respostaConteudo);
        resposta.setComentario(comentario);
    
        
        comentario.setResposta(resposta);
    
        
        commentRepository.save(comentario);
    
        return resposta;
    }
    
}
