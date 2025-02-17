package it.epicode.gestione_viaggi_aziendali.services;

import it.epicode.gestione_viaggi_aziendali.entities.Dipendente;
import it.epicode.gestione_viaggi_aziendali.entities.Prenotazione;
import it.epicode.gestione_viaggi_aziendali.entities.Viaggio;
import it.epicode.gestione_viaggi_aziendali.generals.responses.CreateGeneralResponse;
import it.epicode.gestione_viaggi_aziendali.repositories.DipendenteRepository;
import it.epicode.gestione_viaggi_aziendali.repositories.PrenotazioneRepository;
import it.epicode.gestione_viaggi_aziendali.repositories.ViaggioRepository;
import it.epicode.gestione_viaggi_aziendali.requests.PrenotazioneRequest;
import it.epicode.gestione_viaggi_aziendali.responses.PrenotazioneResponse;
import it.epicode.gestione_viaggi_aziendali.responses.ViaggioResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public CreateGeneralResponse create(@Valid PrenotazioneRequest request) {
        if (prenotazioneRepository.existsByViaggioIdAndDipendenteId(request.getViaggioId(), request.getDipendenteId())) {
            throw new EntityExistsException("Prenotazione con viaggioId " + request.getViaggioId() + " e dipendenteId " + request.getDipendenteId() + " esistente");
        }

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(request, prenotazione);
        prenotazioneRepository.save(prenotazione);
        return new CreateGeneralResponse(prenotazione.getId());
    }


    //metodo per creare un endpoint dedicato per assegnare un dipendente a un viaggio
    public void assegnaDipendenteAViaggio(Long viaggioId, Long dipendenteId, Long prenotazioneId) {

        Dipendente dipendente = dipendenteRepository.findById(dipendenteId).get();

        Viaggio viaggio = viaggioRepository.findById(viaggioId).get();

        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId).get();
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);
        prenotazioneRepository.save(prenotazione);

    }

    public List<PrenotazioneResponse> findAll(){
        return prenotazioneResponseFromEntityList(prenotazioneRepository.findAll());
    }

    public PrenotazioneResponse findById(Long id) {

        if (!prenotazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione con id " + id + " non trovato");
        }
        Prenotazione prenotazione = prenotazioneRepository.findById(id).get();

        PrenotazioneResponse response = new PrenotazioneResponse();

        BeanUtils.copyProperties(prenotazione, response);

        if (prenotazione.getDipendente() != null) {
            response.setDipendenteId(prenotazione.getDipendente().getId());
        }

        if (prenotazione.getViaggio() != null) {
            response.setViaggioId(prenotazione.getViaggio().getId());
        }
        return response;
    }

    public void delete(Long id) {
        if(!prenotazioneRepository.existsById(id)){
            throw new EntityNotFoundException("Prenotazione con id " + id + " non trovata");
        }
        prenotazioneRepository.deleteById(id);
    }

    public void update(PrenotazioneRequest request, Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione con id " + id + " non trovato");
        }
        Prenotazione prenotazione = prenotazioneRepository.findById(id).get();
        BeanUtils.copyProperties(request, prenotazione);
        prenotazioneRepository.save(prenotazione);
    }






    //metodi
    private PrenotazioneResponse prenotazioneResponseFromEntity(Prenotazione prenotazione) {
        PrenotazioneResponse response = new PrenotazioneResponse();
        BeanUtils.copyProperties(prenotazione, response);
        if (prenotazione.getDipendente() != null) {
            response.setDipendenteId(prenotazione.getDipendente().getId());
        }

        if (prenotazione.getViaggio() != null) {
            response.setViaggioId(prenotazione.getViaggio().getId());
        }

        return response;

    }

    private List<PrenotazioneResponse> prenotazioneResponseFromEntityList(List<Prenotazione> prenotazioni) {
        return prenotazioni.stream().map(this::prenotazioneResponseFromEntity).toList();
    }

}

