package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Consultation des paiements")
public class PaiementController {
    private final IPaiementService service;
    @GetMapping
    @Operation(summary = "Lister les paiements")
    public List<Paiement> findAll() { return service.retrieveAllPaiements(); }
    @GetMapping("/{id}")
    @Operation(summary = "Consulter un paiement")
    public Paiement findById(@PathVariable Long id) { return service.retrievePaiement(id); }
}
