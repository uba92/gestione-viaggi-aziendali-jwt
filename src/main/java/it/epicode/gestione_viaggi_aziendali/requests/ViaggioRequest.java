package it.epicode.gestione_viaggi_aziendali.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequest {

    @NotBlank(message = "Il campo destinazione è obbligatorio")
    private String destinazione;

    @Future(message = "La data di partenza non può essere passata o presente")
    private LocalDate dataPartenza;

    @NotBlank(message = "Il campo stato è obbligatorio")
    private String stato;
}
