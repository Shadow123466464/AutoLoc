package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService service;

    @GetMapping
    public List<VehiculeDto> findAll() {
        return service.retrieveAllVehicules().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public VehiculeDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveVehicule(id));
    }

    @PostMapping
    public ResponseEntity<VehiculeDto> create(@RequestBody VehiculeDto value) {
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addVehicule(ApiDtoMapper.toEntity(value))));
    }

    @PutMapping("/{id}")
    public VehiculeDto update(@PathVariable Long id, @RequestBody VehiculeDto value) {
        VehiculeDto request = new VehiculeDto(id, value.immatriculation(), value.marque(), value.modele(),
                value.categorie(), value.tarifJournalier(), value.statut(), value.agenceId());
        return ApiDtoMapper.toDto(service.updateVehicule(ApiDtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
