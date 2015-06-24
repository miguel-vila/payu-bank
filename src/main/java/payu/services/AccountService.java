package payu.services;

import payu.infrastructure.data.AccountReport;
import payu.infrastructure.exceptions.ClientDoesntExistException;
import payu.models.Account;

import java.util.Date;
import java.util.List;

public interface AccountService {

    public Iterable<Account> getClientAccounts(Long clientId);

    public Account createEmptyAccountForClientWithId(Long clientId) throws ClientDoesntExistException;

    public Account getAccount(Long number);

    public void deleteAccount(Long accountId);

    public List<AccountReport> filterAccounts(Long clientId, Date start, Date end) throws ClientDoesntExistException;

}
