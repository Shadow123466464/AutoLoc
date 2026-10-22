package tn.esprit.autoloc.autolocapi.web.dto;

import tn.esprit.autoloc.autolocapi.domain.*;

public final class ApiDtoMapper {

    private ApiDtoMapper() {
    }

    public static Agence toEntity(AgenceDto dto) {
        Agence entity = new Agence();
        entity.setIdAgence(dto.idAgence());
        entity.setNom(dto.nom());
        entity.setVille(dto.ville());
        entity.setAdresse(dto.adresse());
        entity.setTelephone(dto.telephone());
        return entity;
    }

    public static AgenceDto toDto(Agence entity) {
        return new AgenceDto(entity.getIdAgence(), entity.getNom(), entity.getVille(),
                entity.getAdresse(), entity.getTelephone());
    }

    public static Client toEntity(ClientDto dto) {
        Client entity = new Client();
        entity.setIdClient(dto.idClient());
        entity.setNom(dto.nom());
        entity.setPrenom(dto.prenom());
        entity.setEmail(dto.email());
        entity.setTelephone(dto.telephone());
        entity.setNumPermis(dto.numPermis());
        entity.setDateInscription(dto.dateInscription());
        return entity;
    }

    public static ClientDto toDto(Client entity) {
        return new ClientDto(entity.getIdClient(), entity.getNom(), entity.getPrenom(),
                entity.getEmail(), entity.getTelephone(), entity.getNumPermis(), entity.getDateInscription());
    }

    public static Equipement toEntity(EquipementDto dto) {
        Equipement entity = new Equipement();
        entity.setIdEquipement(dto.idEquipement());
        entity.setLibelle(dto.libelle());
        return entity;
    }

    public static EquipementDto toDto(Equipement entity) {
        return new EquipementDto(entity.getIdEquipement(), entity.getLibelle());
    }

    public static Maintenance toEntity(MaintenanceDto dto) {
        Maintenance entity = new Maintenance();
        entity.setIdMaintenance(dto.idMaintenance());
        entity.setDateDebut(dto.dateDebut());
        entity.setDateFin(dto.dateFin());
        entity.setDescription(dto.description());
        if (dto.vehiculeId() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.vehiculeId());
            entity.setVehicule(vehicule);
        }
        return entity;
    }

    public static MaintenanceDto toDto(Maintenance entity) {
        return new MaintenanceDto(entity.getIdMaintenance(), entity.getDateDebut(), entity.getDateFin(),
                entity.getDescription(), entity.getVehicule() == null ? null : entity.getVehicule().getIdVehicule());
    }

    public static Reservation toEntity(ReservationDto dto) {
        Reservation entity = new Reservation();
        entity.setIdReservation(dto.idReservation());
        entity.setDateDebut(dto.dateDebut());
        entity.setDateFin(dto.dateFin());
        entity.setStatut(dto.statut());
        if (dto.clientId() != null) {
            Client client = new Client();
            client.setIdClient(dto.clientId());
            entity.setClient(client);
        }
        if (dto.vehiculeId() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.vehiculeId());
            entity.setVehicule(vehicule);
        }
        return entity;
    }

    public static ReservationDto toDto(Reservation entity) {
        return new ReservationDto(entity.getIdReservation(), entity.getDateDebut(), entity.getDateFin(),
                entity.getStatut(), entity.getClient() == null ? null : entity.getClient().getIdClient(),
                entity.getVehicule() == null ? null : entity.getVehicule().getIdVehicule());
    }

    public static Vehicule toEntity(VehiculeDto dto) {
        Vehicule entity = new Vehicule();
        entity.setIdVehicule(dto.idVehicule());
        entity.setImmatriculation(dto.immatriculation());
        entity.setMarque(dto.marque());
        entity.setModele(dto.modele());
        entity.setCategorie(dto.categorie());
        entity.setTarifJournalier(dto.tarifJournalier());
        entity.setStatut(dto.statut());
        if (dto.agenceId() != null) {
            Agence agence = new Agence();
            agence.setIdAgence(dto.agenceId());
            entity.setAgence(agence);
        }
        return entity;
    }

    public static VehiculeDto toDto(Vehicule entity) {
        return new VehiculeDto(entity.getIdVehicule(), entity.getImmatriculation(), entity.getMarque(),
                entity.getModele(), entity.getCategorie(), entity.getTarifJournalier(), entity.getStatut(),
                entity.getAgence() == null ? null : entity.getAgence().getIdAgence());
    }

    public static Contrat toEntity(ContratDto dto) {
        Contrat entity = new Contrat();
        entity.setIdContrat(dto.idContrat());
        entity.setDateSignature(dto.dateSignature());
        entity.setMontantTotal(dto.montantTotal());
        entity.setValide(dto.valide());
        if (dto.reservationId() != null) {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(dto.reservationId());
            entity.setReservation(reservation);
        }
        return entity;
    }

    public static ContratDto toDto(Contrat entity) {
        return new ContratDto(entity.getIdContrat(), entity.getDateSignature(), entity.getMontantTotal(),
                entity.getValide(), entity.getReservation() == null ? null : entity.getReservation().getIdReservation());
    }

    public static EmployeDto toDto(Employe entity) {
        return new EmployeDto(entity.getIdEmploye(), entity.getNom(), entity.getPrenom(),
                entity.getRole() == null ? null : entity.getRole().name(),
                entity.getAgence() == null ? null : entity.getAgence().getIdAgence());
    }

    public static PaiementDto toDto(Paiement entity) {
        return new PaiementDto(entity.getIdPaiement(), entity.getMontant(), entity.getDatePaiement(),
                entity.getModePaiement(), entity.getContrat() == null ? null : entity.getContrat().getIdContrat());
    }
}
