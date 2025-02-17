package it.epicode.gestione_viaggi_aziendali.repositories;

import it.epicode.gestione_viaggi_aziendali.entities.Dipendente;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    boolean existsByUsername(String username);
}
