package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.service.IReservationService;
import tn.esprit.autoloc.autolocapi.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService service;

    @GetMapping
    public List<ReservationDto> findAll() {
        return service.retrieveAllReservations().stream().map(ApiDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ReservationDto findById(@PathVariable Long id) {
        return ApiDtoMapper.toDto(service.retrieveReservation(id));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> create(@RequestBody ReservationDto value) {
        return ResponseEntity.status(201).body(ApiDtoMapper.toDto(service.addReservation(ApiDtoMapper.toEntity(value))));
    }

    @PutMapping("/{id}")
    public ReservationDto update(@PathVariable Long id, @RequestBody ReservationDto value) {
        ReservationDto request = new ReservationDto(id, value.dateDebut(), value.dateFin(), value.statut(),
                value.clientId(), value.vehiculeId());
        return ApiDtoMapper.toDto(service.updateReservation(ApiDtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeReservation(id);
        return ResponseEntity.noContent().build();
    }
}
