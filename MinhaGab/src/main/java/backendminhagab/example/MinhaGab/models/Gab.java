package backendminhagab.example.MinhaGab.models;

import backendminhagab.example.MinhaGab.Enums.StatusGab;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne; 
import jakarta.persistence.Table;
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
    private GabRequest gabRequest;

    @ManyToOne // Adiciona o relacionamento com User
    @JoinColumn(name = "user_id", nullable = false) // Nome da coluna que irá armazenar o ID do User
    private UserModel user; // Nome da variável que irá referenciar o User

    @Lob
    @Column(name = "pdf_file", nullable = true)
    private byte[] pdfFile;
    
    @Enumerated(EnumType.STRING) // se você estiver usando um Enum
    private StatusGab status;

    @Column(name = "mensagem", columnDefinition = "TEXT", nullable = true)
    private String mensagem;

    @Override
    public String toString() {
        return "Gabs{" +
                "id=" + id +
                ", gabRequest=" + gabRequest +
                ", user=" + user + // Adiciona a informação do User ao toString
                ", status=" + status +
                ", mensagem='" + mensagem + '\'' +
                ", pdfFile=" + (pdfFile != null ? "PDF file with size " + pdfFile.length : "No PDF file") +
                '}';
    }
}
