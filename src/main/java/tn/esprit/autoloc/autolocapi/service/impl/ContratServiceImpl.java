package tn.esprit.autoloc.autolocapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.service.IContratService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    public Contrat addContrat(Contrat contrat) {
        requireValid(contrat);
        contrat.setIdContrat(null);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContrat(Contrat contrat) {
        requireValid(contrat);
        Contrat existing = retrieveContrat(requireId(contrat.getIdContrat()));
        existing.setDateSignature(contrat.getDateSignature());
        existing.setMontantTotal(contrat.getMontantTotal());
        existing.setValide(contrat.getValide());
        existing.setReservation(contrat.getReservation());
        return contratRepository.save(existing);
    }

    @Override
    public Contrat retrieveContrat(Long idContrat) {
        return contratRepository.findById(idContrat)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", idContrat));
    }

    @Override
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public void removeContrat(Long idContrat) {
        retrieveContrat(idContrat);
        contratRepository.deleteById(idContrat);
    }

    private void requireValid(Contrat contrat) {
        if (contrat == null || contrat.getMontantTotal() == null) {
            throw new IllegalArgumentException("Le montant total du contrat est obligatoire");
        }
        if (contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Le montant total du contrat ne peut pas être négatif");
        }
    }

    private Long requireId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("idContrat ne doit pas être null pour une mise à jour");
        }
        return id;
    }
}
