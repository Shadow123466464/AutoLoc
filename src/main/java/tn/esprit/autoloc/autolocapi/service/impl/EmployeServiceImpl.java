package tn.esprit.autoloc.autolocapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.domain.RoleEmploye;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe addEmploye(Employe employe) {
        requireValid(employe);
        employe.setIdEmploye(null);
        employe.setRole(RoleEmploye.AGENT);
        return employeRepository.save(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        requireValid(employe);
        Employe existing = retrieveEmploye(requireId(employe.getIdEmploye()));
        existing.setNom(employe.getNom());
        existing.setPrenom(employe.getPrenom());
        existing.setRole(employe.getRole());
        existing.setAgence(employe.getAgence());
        return employeRepository.save(existing);
    }

    @Override
    public Employe retrieveEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", idEmploye));
    }

    @Override
    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        retrieveEmploye(idEmploye);
        employeRepository.deleteById(idEmploye);
    }

    private void requireValid(Employe employe) {
        if (employe == null || isBlank(employe.getNom())) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
    }

    private Long requireId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("idEmploye ne doit pas être null pour une mise à jour");
        }
        return id;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
