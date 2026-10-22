package tn.esprit.autoloc.autolocapi.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import tn.esprit.autoloc.autolocapi.domain.ModePaiement;

public record PaiementDto(Long idPaiement, BigDecimal montant, LocalDate datePaiement,
                          ModePaiement modePaiement, Long contratId) {
}
