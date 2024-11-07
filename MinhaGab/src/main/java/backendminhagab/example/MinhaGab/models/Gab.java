package backendminhagab.example.MinhaGab.models;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
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
@Table(name = "gab")
public class Gab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false) // Cada Gab deve ter uma GabRequest associada
    private GabRequest gabRequest; // Obrigatório

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Lob
    @Column(name = "pdf_file", columnDefinition = "LONGBLOB", nullable = true) // PDF opcional
    private byte[] pdfFile;

    @Enumerated(EnumType.STRING)
    private StatusGab status; // Status do GAB, para controle de fluxo

    @Column(name = "mensagem", columnDefinition = "TEXT", nullable = true) // Mensagem opcional
    private String mensagem;

    @Override
    public String toString() {
        return "Gab{" +
                "id=" + id +
                ", gabRequest=" + gabRequest +
                ", user=" + user +
                ", status=" + status +
                ", mensagem='" + mensagem + '\'' +
                ", pdfFile=" + (pdfFile != null ? "PDF file with size " + pdfFile.length : "No PDF file") +
                '}';
    }
}
