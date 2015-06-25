package payu.services;

import payu.infrastructure.data.NewMovementData;
import payu.infrastructure.exceptions.InsufficientFundsException;
import payu.infrastructure.exceptions.InvalidMovementTypeException;
import payu.models.Movement;

public interface MovementService {

    public Movement createMovement(Long accountId, NewMovementData newMovementData) throws InsufficientFundsException, InvalidMovementTypeException;

}
