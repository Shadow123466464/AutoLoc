package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService service;

    @GetMapping
    public List<EquipementDto> findAll() {
        return service.retrieveAllEquipements().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public EquipementDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveEquipement(id));
    }

    @PostMapping
    public ResponseEntity<EquipementDto> create(@RequestBody EquipementDto value) {
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addEquipement(ApiDtoMapper.toEntity(value))));
    }

    @PutMapping("/{id}")
    public EquipementDto update(@PathVariable Long id, @RequestBody EquipementDto value) {
        return ApiDtoMapper.toDto(service.updateEquipement(
                ApiDtoMapper.toEntity(new EquipementDto(id, value.libelle()))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
