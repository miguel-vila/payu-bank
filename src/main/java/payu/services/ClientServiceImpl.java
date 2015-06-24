package payu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payu.infrastructure.data.ClientItem;
import payu.infrastructure.exceptions.BadRequestException;
import payu.models.Client;
import payu.persistence.ClientRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepo;

    public Long createClient(Client client) throws BadRequestException {
        if(client.getId()!=null || client.getAccounts()!=null)
            throw new BadRequestException("No need to set id or accounts when creating a client");
        return clientRepo.save(client).getId();
    }

    public void updateClient(Client client) throws BadRequestException {
        if(client.getId() == null)
            throw new BadRequestException("Id is required when updating");
        if(client.getAccounts() != null)
            throw new BadRequestException("No need to send accounts when updating a client");
        clientRepo.save(client);
    }

    public ClientItem findById(Long id){
        Client c = clientRepo.findOne(id);
        if(c == null)
            return null;
        else
            return new ClientItem(c);
    }

    public Iterable<ClientItem> findAll() {
        Iterable<Client> clients = clientRepo.findAll();
        return StreamSupport
                .stream(clients.spliterator(), false)
                .map(completeClient -> new ClientItem(completeClient))
                .collect(Collectors.toList());
    }

    public void deleteClient(Long id) {
        clientRepo.delete(id);
    }
}
