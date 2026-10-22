package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService service;
    @GetMapping public List<Maintenance> findAll() { return service.retrieveAllMaintenances(); }
    @GetMapping("/{id}") public Maintenance findById(@PathVariable Long id) { return service.retrieveMaintenance(id); }
    @PostMapping public ResponseEntity<Maintenance> create(@RequestBody Maintenance value) { return ResponseEntity.status(201).body(service.addMaintenance(value)); }
    @PutMapping("/{id}") public Maintenance update(@PathVariable Long id, @RequestBody Maintenance value) { value.setIdMaintenance(id); return service.updateMaintenance(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeMaintenance(id); return ResponseEntity.noContent().build(); }
}
