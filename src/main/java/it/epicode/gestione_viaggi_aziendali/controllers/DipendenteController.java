package it.epicode.gestione_viaggi_aziendali.controllers;

import it.epicode.gestione_viaggi_aziendali.generals.responses.CreateGeneralResponse;
import it.epicode.gestione_viaggi_aziendali.requests.DipendenteRequest;
import it.epicode.gestione_viaggi_aziendali.responses.DipendenteResponse;
import it.epicode.gestione_viaggi_aziendali.services.DipendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {

    private final DipendenteService dipendenteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public List<DipendenteResponse> findAll() {
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DipendenteResponse findById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CreateGeneralResponse create(@RequestBody DipendenteRequest request) {
        return dipendenteService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody DipendenteRequest request, @PathVariable Long id) {
        dipendenteService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dipendenteService.delete(id);
    }

    @PatchMapping("/{id}/img")
    @ResponseStatus(HttpStatus.OK)
    public void updateDipendenteImg(@PathVariable Long id, String img) {
        dipendenteService.updateDipendenteImg(id, img);
    }
}
