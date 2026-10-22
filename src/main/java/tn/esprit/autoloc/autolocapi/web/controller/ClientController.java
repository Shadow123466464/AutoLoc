package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService service;
    @GetMapping public List<Client> findAll() { return service.retrieveAllClients(); }
    @GetMapping("/{id}") public Client findById(@PathVariable Long id) { return service.retrieveClient(id); }
    @PostMapping public ResponseEntity<Client> create(@RequestBody Client value) { return ResponseEntity.status(201).body(service.addClient(value)); }
    @PutMapping("/{id}") public Client update(@PathVariable Long id, @RequestBody Client value) { value.setIdClient(id); return service.updateClient(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeClient(id); return ResponseEntity.noContent().build(); }
}
