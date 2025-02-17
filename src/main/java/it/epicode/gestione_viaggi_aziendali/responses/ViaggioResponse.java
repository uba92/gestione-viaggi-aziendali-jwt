package it.epicode.gestione_viaggi_aziendali.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioResponse {

    private Long id;
    private String destinazione;
    private LocalDate dataPartenza;
    private String stato;
}
