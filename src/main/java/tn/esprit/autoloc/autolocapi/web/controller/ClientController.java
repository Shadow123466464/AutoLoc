package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.service.IClientService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService service;

    @GetMapping
    public List<ClientDto> findAll() {
        return service.retrieveAllClients().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ClientDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveClient(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto value) {
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addClient(ApiDtoMapper.toEntity(value))));
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable Long id, @RequestBody ClientDto value) {
        ClientDto request = new ClientDto(id, value.nom(), value.prenom(), value.email(), value.telephone(),
                value.numPermis(), value.dateInscription());
        return ApiDtoMapper.toDto(service.updateClient(ApiDtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeClient(id);
        return ResponseEntity.noContent().build();
    }
}
