package it.epicode.gestione_viaggi_aziendali.requests;

import it.epicode.gestione_viaggi_aziendali.entities.Dipendente;
import it.epicode.gestione_viaggi_aziendali.entities.Viaggio;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

    private LocalDate dataRichiesta;
    private String note;

    private Long viaggioId;

    private Long dipendenteId;
}
