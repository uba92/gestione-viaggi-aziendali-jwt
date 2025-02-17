package it.epicode.gestione_viaggi_aziendali.security.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
