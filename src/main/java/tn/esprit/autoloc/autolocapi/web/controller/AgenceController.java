package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {
    private final IAgenceService service;

    @GetMapping
    public List<AgenceDto> findAll() {
        return service.retrieveAllAgences().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public AgenceDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveAgence(id));
    }

    @PostMapping
    public ResponseEntity<AgenceDto> create(@RequestBody AgenceDto value) {
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addAgence(ApiDtoMapper.toEntity(value))));
    }

    @PutMapping("/{id}")
    public AgenceDto update(@PathVariable Long id, @RequestBody AgenceDto value) {
        AgenceDto request = new AgenceDto(id, value.nom(), value.ville(), value.adresse(), value.telephone());
        return ApiDtoMapper.toDto(service.updateAgence(ApiDtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeAgence(id);
        return ResponseEntity.noContent().build();
    }
}
