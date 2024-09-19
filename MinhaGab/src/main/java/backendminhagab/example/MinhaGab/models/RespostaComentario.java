package backendminhagab.example.MinhaGab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "respostas")
public class RespostaComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String conteudo;  

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comentario_id")
    private Comentarios comentario;
}
