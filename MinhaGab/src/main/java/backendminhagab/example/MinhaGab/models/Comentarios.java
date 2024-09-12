package backendminhagab.example.MinhaGab.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import backendminhagab.example.MinhaGab.Enums.StatusComment;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
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

    @OneToOne(mappedBy = "comentarios", cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer answer;
}
