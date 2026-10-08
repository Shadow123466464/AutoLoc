package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Maintenance maintenance);
    Maintenance retrieveMaintenance(Long idMaintenance);
    List<Maintenance> retrieveAllMaintenances();
    void removeMaintenance(Long idMaintenance);
}