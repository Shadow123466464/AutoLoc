package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final IEmployeService service;

    @GetMapping
    public List<EmployeDto> findAll() {
        return service.retrieveAllEmployes().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public EmployeDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveEmploye(id));
    }

    @PostMapping
    public ResponseEntity<EmployeDto> create(@RequestBody EmployeRequest request) {
        Employe value = new Employe();
        value.setNom(request.nom());
        value.setPrenom(request.prenom());
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addEmploye(value)));
    }

    @PutMapping("/{id}")
    public EmployeDto update(@PathVariable Long id, @RequestBody EmployeRequest request) {
        Employe value = service.retrieveEmploye(id);
        value.setNom(request.nom());
        value.setPrenom(request.prenom());
        return ApiDtoMapper.toDto(service.updateEmploye(value));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
