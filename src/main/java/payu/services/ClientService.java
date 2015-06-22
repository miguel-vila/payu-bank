package payu.services;

import payu.models.Client;

public interface ClientService {

   public Client createClient(Client client);

   public Client findById(Long id);

   public Iterable<Client> findAll();

   public void deleteClient(Long id);
}
