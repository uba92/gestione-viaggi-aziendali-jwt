package it.epicode.gestione_viaggi_aziendali.security.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
