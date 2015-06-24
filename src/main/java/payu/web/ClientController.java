package payu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import payu.infrastructure.data.ClientItem;
import payu.infrastructure.exceptions.BadRequestException;
import payu.models.Client;
import payu.services.ClientService;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ClientItem> findAll() {
        return clientService.findAll();
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<String> createClient(@Valid @RequestBody Client client) {
        try {
            Long id= clientService.createClient(client);
            return new ResponseEntity("{ \"id\": "+id+" }", HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<String>("\""+e.getMessage()+"\"", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateClient(@PathVariable Long id, @Valid @RequestBody Client client) {
        assert(id == client.getId());
        try {
            clientService.updateClient(client);
            return new ResponseEntity("\"Client updated successfully\"", HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<String>("\""+e.getMessage()+"\"", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        ClientItem client = clientService.findById(id);
        HttpStatus status = client == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(client, status);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity("\"Client successfully deleted\"",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("\"Error deleting client: "+e.getMessage()+"\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
