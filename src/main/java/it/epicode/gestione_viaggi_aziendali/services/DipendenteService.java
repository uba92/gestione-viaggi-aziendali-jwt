package it.epicode.gestione_viaggi_aziendali.services;

import it.epicode.gestione_viaggi_aziendali.entities.Dipendente;
import it.epicode.gestione_viaggi_aziendali.generals.responses.CreateGeneralResponse;
import it.epicode.gestione_viaggi_aziendali.repositories.DipendenteRepository;
import it.epicode.gestione_viaggi_aziendali.requests.DipendenteRequest;
import it.epicode.gestione_viaggi_aziendali.responses.DipendenteResponse;
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
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public CreateGeneralResponse create(@Valid DipendenteRequest request) {
        if (dipendenteRepository.existsByUsername(request.getUsername())) {
            throw new EntityExistsException("Dipendente con username " + request.getUsername() + " già esistente");
        }

        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(request, dipendente);
        dipendenteRepository.save(dipendente);
        return new CreateGeneralResponse(dipendente.getId());
    }

    public List<DipendenteResponse> findAll() {
        return dipendenteResponseFromEntityList(dipendenteRepository.findAll());
    }

    public DipendenteResponse findById(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente con id " + id + " non trovato");
        }

        Dipendente dipendente = dipendenteRepository.findById(id).get();
        DipendenteResponse response = new DipendenteResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }

    public void delete(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente con id " + id + " non trovato");
        }
        dipendenteRepository.deleteById(id);
    }

    public void update(@Valid DipendenteRequest request, Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente con id " + id + " non trovato");
        }

        Dipendente dipendente = dipendenteRepository.findById(id).get();
        BeanUtils.copyProperties(request, dipendente);
        dipendenteRepository.save(dipendente);
    }

    public DipendenteResponse updateDipendenteImg(Long id, String img) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente con id " + id + " non trovato");
        }
        Dipendente dipendente = dipendenteRepository.findById(id).get();
        dipendente.setImgUrl(img);
        dipendenteRepository.save(dipendente);
        return dipendenteResponseFromEntity(dipendente);
    }

    // Metodi
    private DipendenteResponse dipendenteResponseFromEntity(Dipendente dipendente) {
        DipendenteResponse response = new DipendenteResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }

    private List<DipendenteResponse> dipendenteResponseFromEntityList(List<Dipendente> dipendenti) {
        return dipendenti.stream().map(this::dipendenteResponseFromEntity).toList();
    }
}
