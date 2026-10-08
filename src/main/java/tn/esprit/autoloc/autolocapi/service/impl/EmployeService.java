package tn.esprit.autoloc.autolocapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeService implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe addEmploye(Employe employe) {
        employe.setIdEmploye(null);
        return employeRepository.save(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        if (employe.getIdEmploye() == null) {
            throw new IllegalArgumentException("idEmploye must not be null for update");
        }
        if (!employeRepository.existsById(employe.getIdEmploye())) {
            throw new EntityNotFoundException("Employe not found id=" + employe.getIdEmploye());
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe retrieveEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe not found id=" + idEmploye));
    }

    @Override
    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        employeRepository.deleteById(idEmploye);
    }
}