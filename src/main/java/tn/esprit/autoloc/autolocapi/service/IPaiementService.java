package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Paiement;
import java.util.List;

public interface IPaiementService {
    Paiement retrievePaiement(Long idPaiement);
    List<Paiement> retrieveAllPaiements();
}