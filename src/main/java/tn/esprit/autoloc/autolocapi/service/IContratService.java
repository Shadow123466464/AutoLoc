package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Contrat;
import java.util.List;

public interface IContratService {
    Contrat addContrat(Contrat contrat);
    Contrat updateContrat(Contrat contrat);
    Contrat retrieveContrat(Long idContrat);
    List<Contrat> retrieveAllContrats();
    void removeContrat(Long idContrat);
}