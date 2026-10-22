package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats de location")
public class ContratController {
    private final IContratService service;
    @GetMapping
    @Operation(summary = "Lister les contrats")
    public List<Contrat> findAll() { return service.retrieveAllContrats(); }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un contrat")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")})
    public Contrat findById(@PathVariable Long id) { return service.retrieveContrat(id); }

    @PostMapping
    @Operation(summary = "Créer un contrat")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Contrat créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    public ResponseEntity<Contrat> create(@RequestBody Contrat value) { return ResponseEntity.status(201).body(service.addContrat(value)); }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Contrat modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")})
    public Contrat update(@PathVariable Long id, @RequestBody Contrat value) { value.setIdContrat(id); return service.updateContrat(value); }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Contrat supprimé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")})
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeContrat(id); return ResponseEntity.noContent().build(); }
}
