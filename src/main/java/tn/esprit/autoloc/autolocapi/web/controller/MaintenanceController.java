package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService service;

    @GetMapping
    public List<MaintenanceDto> findAll() {
        return service.retrieveAllMaintenances().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public MaintenanceDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveMaintenance(id));
    }

    @PostMapping
    public ResponseEntity<MaintenanceDto> create(@RequestBody MaintenanceDto value) {
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addMaintenance(ApiDtoMapper.toEntity(value))));
    }

    @PutMapping("/{id}")
    public MaintenanceDto update(@PathVariable Long id, @RequestBody MaintenanceDto value) {
        MaintenanceDto request = new MaintenanceDto(id, value.dateDebut(), value.dateFin(),
                value.description(), value.vehiculeId());
        return ApiDtoMapper.toDto(service.updateMaintenance(ApiDtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
