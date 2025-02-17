package it.epicode.gestione_viaggi_aziendali.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dipendenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //username, nome, cognome, email
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String imgUrl;

    @OneToMany(mappedBy = "dipendente")
    @ToString.Exclude
    @JsonIgnoreProperties("dipendente")
    private List<Prenotazione> prenotazioni = new ArrayList<>();
}
