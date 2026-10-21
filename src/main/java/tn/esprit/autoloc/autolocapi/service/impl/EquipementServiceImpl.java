package tn.esprit.autoloc.autolocapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement addEquipement(Equipement equipement) {
        requireValid(equipement);
        equipement.setIdEquipement(null);
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement updateEquipement(Equipement equipement) {
        requireValid(equipement);
        Equipement existing = retrieveEquipement(requireId(equipement.getIdEquipement()));
        existing.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existing);
    }

    @Override
    public Equipement retrieveEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
    }

    @Override
    public List<Equipement> retrieveAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public void removeEquipement(Long idEquipement) {
        retrieveEquipement(idEquipement);
        equipementRepository.deleteById(idEquipement);
    }

    private void requireValid(Equipement equipement) {
        if (equipement == null || equipement.getLibelle() == null || equipement.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
    }

    private Long requireId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("idEquipement ne doit pas être null pour une mise à jour");
        }
        return id;
    }
}
