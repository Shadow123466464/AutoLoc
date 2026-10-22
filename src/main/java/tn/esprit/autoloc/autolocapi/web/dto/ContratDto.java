package tn.esprit.autoloc.autolocapi.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratDto(Long idContrat, LocalDate dateSignature, BigDecimal montantTotal,
                         Boolean valide, Long reservationId) {
}
