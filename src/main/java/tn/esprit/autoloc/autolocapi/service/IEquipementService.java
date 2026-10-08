package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement addEquipement(Equipement equipement);
    Equipement updateEquipement(Equipement equipement);
    Equipement retrieveEquipement(Long idEquipement);
    List<Equipement> retrieveAllEquipements();
    void removeEquipement(Long idEquipement);
}