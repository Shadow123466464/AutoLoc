# Atelier 5 — API REST, Postman et SpringDoc

## A. Endpoints

| Contrôleur | Verbe et route | Service | Succès | Erreurs |
|---|---|---|---|---|
| ContratController | GET `/api/contrats` | `retrieveAllContrats()` | 200 | — |
| ContratController | GET `/api/contrats/{id}` | `retrieveContrat(id)` | 200 | 404 |
| ContratController | POST `/api/contrats` | `addContrat()` | 201 | 400 |
| ContratController | PUT `/api/contrats/{id}` | `updateContrat()` | 200 | 400, 404 |
| ContratController | DELETE `/api/contrats/{id}` | `removeContrat()` | 204 | 404 |
| PaiementController | GET `/api/paiements` | `retrieveAllPaiements()` | 200 | — |
| PaiementController | GET `/api/paiements/{id}` | `retrievePaiement(id)` | 200 | 404 |
| Autres contrôleurs | Même schéma CRUD avec leur ressource plurielle | Interface de service correspondante | 200, 201, 204 | 400, 404 |

## B. Associations JSON

`Contrat.paiements` utilise `@JsonManagedReference` et `Paiement.contrat` utilise
`@JsonBackReference`. Le contrat est le parent JSON et le paiement est le côté inverse ;
cela évite la boucle de sérialisation et permet la cascade de création/suppression.
Les collections inverses d'`Agence`, `Client` et `Vehicule` utilisent `@JsonIgnore` pour
ne pas exposer tout le graphe JPA dans chaque réponse.

`@JsonBackReference` ignore le côté inverse d'une paire managed/back reference.
`@JsonIgnore` ignore toujours la propriété concernée dans la sérialisation et la désérialisation.

## C. Postman et Swagger

Postman envoie des requêtes répétables et vérifie les statuts avec des scripts, par exemple
201 après une création et 404 pour une ressource absente. Swagger UI documente les routes
générées par SpringDoc et permet aussi de les essayer. Les deux outils sont complémentaires.

## D. Anomalies SonarQube

| Problème | Correction |
|---|---|
| Identifiants MySQL en clair | Variables `DB_USERNAME` et `DB_PASSWORD` |
| Logs SQL activés dans la configuration principale | Logs déplacés dans le profil `dev` |
| Exceptions de persistance non traduites en 404 | Utilisation de `ResourceNotFoundException` |

## E. Compréhension

1. `DispatcherServlet` reçoit chaque requête et utilise les mappings HTTP/URL des contrôleurs
   pour choisir la méthode à appeler.
2. Le contrôleur dépend de `IContratService` afin de rester indépendant de l'implémentation
   et du repository.
3. POST renvoie 201 car une ressource est créée ; DELETE renvoie 204 car la suppression
   réussit sans corps. Une ressource absente est une erreur client 404, pas une panne serveur 500.
4. Paiement est en lecture seule car il est géré par le contrat et sa cascade. Un POST
   `/api/paiements` n'est pas mappé et renvoie 405.
5. Exposer directement les entités peut révéler des champs internes et créer des boucles JSON.
   La paire managed/back reference est utilisée pour Contrat/Paiement ; `@JsonIgnore` masque
   les collections inverses qui ne doivent pas être renvoyées.
