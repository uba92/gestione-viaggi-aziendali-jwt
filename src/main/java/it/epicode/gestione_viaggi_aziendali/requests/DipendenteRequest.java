package it.epicode.gestione_viaggi_aziendali.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteRequest {

    @NotBlank(message = "Il campo username è obbligatorio")
    private String username;
    @NotBlank(message = "Il campo nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Il campo cognome è obbligatorio")
    private String cognome;
    @Email(message = "Formato email non valido")
    @NotBlank(message = "Il campo email è obbligatorio")
    private String email;

    private String imgUrl;
}
