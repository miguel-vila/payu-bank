package payu.services;

import payu.infrastructure.data.NewMovementData;
import payu.infrastructure.exceptions.InsufficientFundsException;
import payu.infrastructure.exceptions.InvalidMovementTypeException;

public interface MovementService {

    public void createMovement(Long accountId, NewMovementData newMovementData) throws InsufficientFundsException, InvalidMovementTypeException;

}
