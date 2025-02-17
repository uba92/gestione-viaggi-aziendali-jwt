package it.epicode.gestione_viaggi_aziendali.controllers;


import it.epicode.gestione_viaggi_aziendali.generals.responses.CreateGeneralResponse;
import it.epicode.gestione_viaggi_aziendali.requests.StatoViaggioRequest;
import it.epicode.gestione_viaggi_aziendali.requests.ViaggioRequest;
import it.epicode.gestione_viaggi_aziendali.responses.ViaggioResponse;
import it.epicode.gestione_viaggi_aziendali.services.ViaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    private final ViaggioService viaggioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ViaggioResponse> findAll() {
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ViaggioResponse findById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateGeneralResponse create(@RequestBody ViaggioRequest request) {
        return viaggioService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ViaggioRequest request, @PathVariable Long id) {
        viaggioService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }

    @PutMapping("/{id}/stato")
    @ResponseStatus(HttpStatus.OK)
    public void updateStato(@PathVariable Long id, @RequestBody StatoViaggioRequest request) {
        viaggioService.updateStato(id, request);
    }
}
