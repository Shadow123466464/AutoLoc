package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Client;

import java.util.List;

public interface IClientService {
    Client addClient(Client client);
    Client updateClient(Client client);
    Client retrieveClient(Long idClient);
    List<Client> retrieveAllClients();
    void removeClient(Long idClient);
}