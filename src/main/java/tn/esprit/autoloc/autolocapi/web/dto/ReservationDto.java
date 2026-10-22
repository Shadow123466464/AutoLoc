package tn.esprit.autoloc.autolocapi.web.dto;

import java.time.LocalDate;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

public record ReservationDto(Long idReservation, LocalDate dateDebut, LocalDate dateFin,
                             StatutReservation statut, Long clientId, Long vehiculeId) {
}
