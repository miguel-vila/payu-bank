package payu.services;

import payu.infrastructure.data.ClientItem;
import payu.infrastructure.exceptions.BadRequestException;
import payu.models.Client;

public interface ClientService {

   public Long createClient(Client client) throws BadRequestException;

   public void updateClient(Client client) throws BadRequestException;

   public ClientItem findById(Long id);

   public Iterable<ClientItem> findAll();

   public void deleteClient(Long id);
}
