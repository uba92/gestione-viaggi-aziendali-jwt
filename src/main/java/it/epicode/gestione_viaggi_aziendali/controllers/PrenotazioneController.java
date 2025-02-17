package it.epicode.gestione_viaggi_aziendali.controllers;

import it.epicode.gestione_viaggi_aziendali.entities.Prenotazione;
import it.epicode.gestione_viaggi_aziendali.generals.responses.CreateGeneralResponse;
import it.epicode.gestione_viaggi_aziendali.requests.PrenotazioneRequest;
import it.epicode.gestione_viaggi_aziendali.responses.PrenotazioneResponse;
import it.epicode.gestione_viaggi_aziendali.services.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PrenotazioneResponse> findAll() {
        return prenotazioneService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneResponse findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateGeneralResponse create(@RequestBody PrenotazioneRequest request) {
        return prenotazioneService.create(request);
    }

    @PutMapping("/viaggio/{viaggioId}/dipendente/{dipendenteId}/prenotazione/{prenotazioneId}")
    @ResponseStatus(HttpStatus.OK)
    public void assegnaDipendenteAViaggio(@PathVariable Long viaggioId, @PathVariable Long dipendenteId, @PathVariable Long prenotazioneId) {
        {
            prenotazioneService.assegnaDipendenteAViaggio(viaggioId, dipendenteId, prenotazioneId);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        prenotazioneService.delete(id);
    }

}