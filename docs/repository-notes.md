# Atelier 3 — Repository notes

## Choix d’interface

| Interface | Étend | Justification |
|----------|-------|---------------|
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet + List + tri/pagination si besoin |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet + List + tri/pagination |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet + List + tri/pagination |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet + List + tri/pagination |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet + List + tri/pagination |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet + List + tri/pagination |
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet + saveAndFlush + List + tri/pagination |
| IPaiementRepository | JpaRepository<Paiement, Long> | CRUD/lecture des paiements |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet + List + tri/pagination |

## Anomalies SonarQube for IDE corrigées

| Anomalie | Règle / explication | Correction apportée |
|---------|----------------------|---------------------|
| Import inutilisé `java.util.List` dans Agence.java | Code smell : import non utilisé | Suppression de l’import |
| Import inutilisé `java.util.List` dans Client.java | Code smell : import non utilisé | Suppression de l’import |
| Import inutilisé `java.util.List` dans Contrat.java | Code smell : import non utilisé | Suppression de l’import |