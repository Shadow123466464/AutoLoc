package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Agence;
import java.util.List;

public interface IAgenceService {
    Agence addAgence(Agence agence);
    Agence updateAgence(Agence agence);
    Agence retrieveAgence(Long idAgence);
    List<Agence> retrieveAllAgences();
    void removeAgence(Long idAgence);
}