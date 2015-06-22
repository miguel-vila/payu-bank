package payu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import payu.models.Client;
import payu.services.ClientService;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Client> findAll() {
        return clientService.findAll();
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<String> createClient(@Valid @RequestBody Client client) {
        try {
            Client created = clientService.createClient(client);
            return new ResponseEntity("Client successfully created with id: "+created.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error when creating client: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if(client == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(client, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity("Client successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error deleting client: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
