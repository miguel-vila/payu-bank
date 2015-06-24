package payu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import payu.infrastructure.exceptions.ClientDoesntExistException;
import payu.models.Account;
import payu.services.AccountService;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/clients/{clientId}/accounts", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Account> getClientAccounts(@PathVariable Long clientId) {
        return accountService.getClientAccounts(clientId);
    }

    @RequestMapping(value = "/clients/{clientId}/accounts", method = RequestMethod.POST)
    public ResponseEntity<String> createClientAccount(@PathVariable Long clientId) {
        try {
            Account created = accountService.createEmptyAccountForClientWithId(clientId);
            return new ResponseEntity("{ \"id\" : "+created.getNumber()+" }", HttpStatus.CREATED);
        } catch (ClientDoesntExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("\"Error adding account: "+e.getMessage()+"\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        HttpStatus status = account == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(account, status);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity("\"Account deleted successfully\"",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("\"Error deleting account: "+e.getMessage()+"\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
