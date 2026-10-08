package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Employe;
import java.util.List;

public interface IEmployeService {
    Employe addEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    Employe retrieveEmploye(Long idEmploye);
    List<Employe> retrieveAllEmployes();
    void removeEmploye(Long idEmploye);
}