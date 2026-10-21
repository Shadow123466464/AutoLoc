package tn.esprit.autoloc.autolocapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.service.IClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        requireValid(client);
        client.setIdClient(null);
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        requireValid(client);
        Client existing = retrieveClient(requireId(client.getIdClient(), "idClient"));
        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setEmail(client.getEmail());
        existing.setTelephone(client.getTelephone());
        existing.setNumPermis(client.getNumPermis());
        existing.setDateInscription(client.getDateInscription());
        return clientRepository.save(existing);
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", idClient));
    }

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void removeClient(Long idClient) {
        retrieveClient(idClient);
        clientRepository.deleteById(idClient);
    }

    private void requireValid(Client client) {
        if (client == null || isBlank(client.getNom()) || isBlank(client.getEmail())) {
            throw new IllegalArgumentException("Le nom et l'e-mail du client sont obligatoires");
        }
    }

    private Long requireId(Long id, String name) {
        if (id == null) {
            throw new IllegalArgumentException(name + " ne doit pas être null pour une mise à jour");
        }
        return id;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
