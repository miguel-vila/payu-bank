package payu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payu.infrastructure.data.NewMovementData;
import payu.infrastructure.exceptions.InsufficientFundsException;
import payu.infrastructure.exceptions.InvalidMovementTypeException;
import payu.models.Account;
import payu.models.Movement;
import payu.persistence.AccountRepository;
import payu.persistence.MovementRepository;
import javax.transaction.Transactional;

import java.util.Date;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private MovementRepository movementRepo;

    public void createMovement(Long accountId, NewMovementData newMovementData) throws InsufficientFundsException, InvalidMovementTypeException {
        Account acc = accountRepo.findOne(accountId);
        Movement mv = newMovementData.toMovement();
        saveMovement(acc, mv);
    }

    @Transactional
    private void saveMovement(Account account, Movement movement) throws InsufficientFundsException {
        account.applyMovement(movement);
        movement.setAccount(account);
        accountRepo.save(account);
        movementRepo.save(movement);
    }
}
