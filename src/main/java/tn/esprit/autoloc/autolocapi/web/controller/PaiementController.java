package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final IPaiementService service;
    @GetMapping public List<Paiement> findAll() { return service.retrieveAllPaiements(); }
    @GetMapping("/{id}") public Paiement findById(@PathVariable Long id) { return service.retrievePaiement(id); }
}
