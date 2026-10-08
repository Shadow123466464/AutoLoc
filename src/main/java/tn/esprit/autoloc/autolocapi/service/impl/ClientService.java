package tn.esprit.autoloc.autolocapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.service.IClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        client.setIdClient(null);
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        if (client.getIdClient() == null) {
            throw new IllegalArgumentException("idClient must not be null for update");
        }
        if (!clientRepository.existsById(client.getIdClient())) {
            throw new EntityNotFoundException("Client not found id=" + client.getIdClient());
        }
        return clientRepository.save(client);
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client not found id=" + idClient));
    }

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void removeClient(Long idClient) {
        clientRepository.deleteById(idClient);
    }
}