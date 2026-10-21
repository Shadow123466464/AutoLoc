package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final IEmployeService service;
    @GetMapping public List<Employe> findAll() { return service.retrieveAllEmployes(); }
    @GetMapping("/{id}") public Employe findById(@PathVariable Long id) { return service.retrieveEmploye(id); }
    @PostMapping public ResponseEntity<Employe> create(@RequestBody Employe value) { return ResponseEntity.ok(service.addEmploye(value)); }
    @PutMapping("/{id}") public Employe update(@PathVariable Long id, @RequestBody Employe value) { value.setIdEmploye(id); return service.updateEmploye(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeEmploye(id); return ResponseEntity.noContent().build(); }
}
