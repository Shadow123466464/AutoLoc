package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {
    private final IAgenceService service;

    @GetMapping
    public List<Agence> findAll() { return service.retrieveAllAgences(); }
    @GetMapping("/{id}") public Agence findById(@PathVariable Long id) { return service.retrieveAgence(id); }
    @PostMapping public ResponseEntity<Agence> create(@RequestBody Agence value) {
        return ResponseEntity.ok(service.addAgence(value));
    }
    @PutMapping("/{id}") public Agence update(@PathVariable Long id, @RequestBody Agence value) {
        value.setIdAgence(id); return service.updateAgence(value);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeAgence(id); return ResponseEntity.noContent().build();
    }
}
