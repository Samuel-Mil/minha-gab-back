package backendminhagab.example.MinhaGab.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "gabs_requests")
public class GabRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne // Relacionamento ManyToOne com UserModel
    @JoinColumn(name = "patient_id", nullable = false) // Não pode ser nulo
    private UserModel user;

    @OneToOne(mappedBy = "gabRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Gab gab; // Um GabRequest pode ter apenas um Gab associado
}
