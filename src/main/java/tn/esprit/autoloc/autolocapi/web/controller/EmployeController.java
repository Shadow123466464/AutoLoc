package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;
import tn.esprit.autoloc.autolocapi.web.dto.EmployeRequest;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final IEmployeService service;
    @GetMapping public List<Employe> findAll() { return service.retrieveAllEmployes(); }
    @GetMapping("/{id}") public Employe findById(@PathVariable Long id) { return service.retrieveEmploye(id); }
    @PostMapping
    public ResponseEntity<Employe> create(@RequestBody EmployeRequest request) {
        Employe value = new Employe();
        value.setNom(request.nom());
        value.setPrenom(request.prenom());
        return ResponseEntity.status(201).body(service.addEmploye(value));
    }
    @PutMapping("/{id}")
    public Employe update(@PathVariable Long id, @RequestBody EmployeRequest request) {
        Employe value = service.retrieveEmploye(id);
        value.setNom(request.nom());
        value.setPrenom(request.prenom());
        return service.updateEmploye(value);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeEmploye(id); return ResponseEntity.noContent().build(); }
}
