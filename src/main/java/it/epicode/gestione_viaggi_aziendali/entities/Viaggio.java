package it.epicode.gestione_viaggi_aziendali.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "viaggi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinazione;
    private LocalDate dataPartenza;
    private String stato;

    @OneToMany(mappedBy = "viaggio")
    @ToString.Exclude
    @JsonIgnoreProperties("viaggio")
    private List<Prenotazione> prenotazioni = new ArrayList<>();
}
