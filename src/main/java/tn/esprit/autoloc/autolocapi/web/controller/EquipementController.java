package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService service;
    @GetMapping public List<Equipement> findAll() { return service.retrieveAllEquipements(); }
    @GetMapping("/{id}") public Equipement findById(@PathVariable Long id) { return service.retrieveEquipement(id); }
    @PostMapping public ResponseEntity<Equipement> create(@RequestBody Equipement value) { return ResponseEntity.status(201).body(service.addEquipement(value)); }
    @PutMapping("/{id}") public Equipement update(@PathVariable Long id, @RequestBody Equipement value) { value.setIdEquipement(id); return service.updateEquipement(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeEquipement(id); return ResponseEntity.noContent().build(); }
}
