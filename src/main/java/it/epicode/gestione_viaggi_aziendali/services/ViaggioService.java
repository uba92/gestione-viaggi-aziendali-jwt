package it.epicode.gestione_viaggi_aziendali.services;

import it.epicode.gestione_viaggi_aziendali.entities.Viaggio;
import it.epicode.gestione_viaggi_aziendali.generals.responses.CreateGeneralResponse;
import it.epicode.gestione_viaggi_aziendali.repositories.ViaggioRepository;
import it.epicode.gestione_viaggi_aziendali.requests.StatoViaggioRequest;
import it.epicode.gestione_viaggi_aziendali.requests.ViaggioRequest;
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
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;


    public CreateGeneralResponse create(@Valid ViaggioRequest request) {
        if(viaggioRepository.existsByDestinazione(request.getDestinazione())) {
            throw new EntityExistsException("Viaggio con destinazione " + request.getDestinazione() + " già esistente");
        }

        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
        return new CreateGeneralResponse(viaggio.getId());
    }

    public List<ViaggioResponse> findAll() {
        return viaggioResponseFromEntityList(viaggioRepository.findAll());

    }

    public ViaggioResponse findById(Long id) {

        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio con id " + id + " non trovato");
        }

        Viaggio viaggio = viaggioRepository.findById(id).get();
        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;

    }

    public void delete(Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio con id " + id + " non trovato");
        }
        viaggioRepository.deleteById(id);
    }

    public void update(@Valid ViaggioRequest request, Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio con id " + id + " non trovato");
        }
        Viaggio viaggio = viaggioRepository.findById(id).get();
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
    }

    public void updateStato(Long id, StatoViaggioRequest request) {
        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio con id " + id + " non trovato");
        }
        Viaggio viaggio = viaggioRepository.findById(id).get();
        viaggio.setStato(request.getStato());
        viaggioRepository.save(viaggio);
    }

    //metodi
    private ViaggioResponse viaggioResponseFromEntity(Viaggio viaggio) {
        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;

    }

    private List<ViaggioResponse> viaggioResponseFromEntityList(List<Viaggio> viaggi) {
        return viaggi.stream().map(this::viaggioResponseFromEntity).toList();
    }

}
