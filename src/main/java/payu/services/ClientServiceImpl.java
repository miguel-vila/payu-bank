package payu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payu.models.Client;
import payu.persistence.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepo;

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public Client findById(Long id){
        return clientRepo.findOne(id);
    }

    public Iterable<Client> findAll() {
        return clientRepo.findAll();
    }

    public void deleteClient(Long id) {
        clientRepo.delete(id);
    }
}
