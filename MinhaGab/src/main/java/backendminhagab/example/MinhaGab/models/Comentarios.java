package backendminhagab.example.MinhaGab.models;

import backendminhagab.example.MinhaGab.Enums.StatusComment;
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
@Table(name = "comentarios")
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserModel user;

    private String comentarios;

    @Enumerated(EnumType.STRING)
    private StatusComment status;
    
    @OneToOne(mappedBy = "comentario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RespostaComentario resposta;
}
