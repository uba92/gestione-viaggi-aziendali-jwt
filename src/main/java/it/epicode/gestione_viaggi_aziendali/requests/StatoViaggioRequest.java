package it.epicode.gestione_viaggi_aziendali.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatoViaggioRequest {
    @NotBlank(message = "Il campo stato è obbligatorio")
    private String stato;
}
