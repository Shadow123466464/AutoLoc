package tn.esprit.autoloc.autolocapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.repository.IPaiementRepository;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementService implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement addPaiement(Paiement paiement) {
        paiement.setIdPaiement(null);
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        if (paiement.getIdPaiement() == null) {
            throw new IllegalArgumentException("idPaiement must not be null for update");
        }
        if (!paiementRepository.existsById(paiement.getIdPaiement())) {
            throw new EntityNotFoundException("Paiement not found id=" + paiement.getIdPaiement());
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement retrievePaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new EntityNotFoundException("Paiement not found id=" + idPaiement));
    }

    @Override
    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public void removePaiement(Long idPaiement) {
        paiementRepository.deleteById(idPaiement);
    }
}