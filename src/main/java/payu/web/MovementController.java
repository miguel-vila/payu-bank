package payu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import payu.infrastructure.data.NewMovementData;
import payu.infrastructure.exceptions.InsufficientFundsException;
import payu.infrastructure.exceptions.InvalidMovementTypeException;
import payu.services.MovementService;

import javax.validation.Valid;

@Controller
public class MovementController {

    @Autowired
    private MovementService movementService;

    @RequestMapping(value = "/accounts/{accountId}/movements", method = RequestMethod.POST)
    public ResponseEntity<String> createMovement(
            @PathVariable Long accountId,
            @Valid @RequestBody NewMovementData newMovementData) {
        if(newMovementData.getAmmount() == null || newMovementData.getType() == null){
            return new ResponseEntity("Required 'ammount' and 'type' fields", HttpStatus.BAD_REQUEST);
        }
        try {
            movementService.createMovement(accountId, newMovementData);
            return new ResponseEntity("Movement created successfully", HttpStatus.CREATED);
        } catch (InvalidMovementTypeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
