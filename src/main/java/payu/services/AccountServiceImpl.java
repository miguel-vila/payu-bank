package payu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payu.infrastructure.data.AccountReport;
import payu.infrastructure.exceptions.ClientDoesntExistException;
import payu.models.Account;
import payu.models.Client;
import payu.persistence.AccountRepository;
import payu.persistence.ClientRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AccountRepository accountRepository;

    public Account createEmptyAccountForClientWithId(Long clientId) throws ClientDoesntExistException {
        Client client = clientRepo.findOne(clientId);
        if(client == null){
            throw new ClientDoesntExistException(clientId);
        } else {
            Account acc = new Account(client);
            return accountRepository.save(acc);
        }
    }

    public void deleteAccount(Long accountId) {
        accountRepository.delete(accountId);
    }

    public List<AccountReport> filterAccounts(Long clientId, Date start, Date end) throws ClientDoesntExistException {
        Client client = clientRepo.findOne(clientId);
        if(client == null){
            throw new ClientDoesntExistException(clientId);
        } else {
            return client.getAccounts()
                    .stream()
                    .map(account -> AccountReport.fromAccount(account, start, end))
                    .collect(Collectors.toList());
        }
    }

}
