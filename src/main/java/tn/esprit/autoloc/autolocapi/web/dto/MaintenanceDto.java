package tn.esprit.autoloc.autolocapi.web.dto;

import java.time.LocalDate;

public record MaintenanceDto(Long idMaintenance, LocalDate dateDebut, LocalDate dateFin,
                             String description, Long vehiculeId) {
}
