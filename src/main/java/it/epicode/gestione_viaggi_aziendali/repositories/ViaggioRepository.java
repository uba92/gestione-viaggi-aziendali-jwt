package it.epicode.gestione_viaggi_aziendali.repositories;

import it.epicode.gestione_viaggi_aziendali.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {

    boolean existsByDestinazione(String destinazione);
}
