package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService service;
    @GetMapping public List<Contrat> findAll() { return service.retrieveAllContrats(); }
    @GetMapping("/{id}") public Contrat findById(@PathVariable Long id) { return service.retrieveContrat(id); }
    @PostMapping public ResponseEntity<Contrat> create(@RequestBody Contrat value) { return ResponseEntity.ok(service.addContrat(value)); }
    @PutMapping("/{id}") public Contrat update(@PathVariable Long id, @RequestBody Contrat value) { value.setIdContrat(id); return service.updateContrat(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeContrat(id); return ResponseEntity.noContent().build(); }
}
