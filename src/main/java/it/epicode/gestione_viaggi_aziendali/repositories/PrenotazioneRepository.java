package it.epicode.gestione_viaggi_aziendali.repositories;

import it.epicode.gestione_viaggi_aziendali.entities.Dipendente;
import it.epicode.gestione_viaggi_aziendali.entities.Prenotazione;
import it.epicode.gestione_viaggi_aziendali.entities.Viaggio;
import jakarta.validation.constraints.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByViaggioIdAndDipendenteId(Long viaggioId, Long dipendenteId);

}
