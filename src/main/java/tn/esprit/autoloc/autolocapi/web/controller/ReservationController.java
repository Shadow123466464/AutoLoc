package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService service;
    @GetMapping public List<Reservation> findAll() { return service.retrieveAllReservations(); }
    @GetMapping("/{id}") public Reservation findById(@PathVariable Long id) { return service.retrieveReservation(id); }
    @PostMapping public ResponseEntity<Reservation> create(@RequestBody Reservation value) { return ResponseEntity.status(201).body(service.addReservation(value)); }
    @PutMapping("/{id}") public Reservation update(@PathVariable Long id, @RequestBody Reservation value) { value.setIdReservation(id); return service.updateReservation(value); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.removeReservation(id); return ResponseEntity.noContent().build(); }
}
