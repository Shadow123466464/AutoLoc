package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService service;
    @GetMapping public List<Vehicule> findAll() { return service.retrieveAllVehicules(); }
    @GetMapping("/{id}") public Vehicule findById(@PathVariable Long id) { return service.retrieveVehicule(id); }
    @PostMapping public ResponseEntity<Vehicule> create(@RequestBody Vehicule value) { return ResponseEntity.ok(service.addVehicule(value)); }
    @PutMapping("/{id}") public Vehicule update(@PathVariable Long id, @RequestBody Vehicule value) { value.setIdVehicule(id); return service.updateVehicule(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeVehicule(id); return ResponseEntity.noContent().build(); }
}
