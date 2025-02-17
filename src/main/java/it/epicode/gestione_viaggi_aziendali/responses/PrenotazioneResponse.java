package it.epicode.gestione_viaggi_aziendali.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {

    private Long id;
    private String note;
    private LocalDate dataRichiesta;
    private Long dipendenteId;
    private Long viaggioId;
}
