package payu.infrastructure.exceptions;

public class InsufficientFundsException extends PayuBankException {

    public InsufficientFundsException(Long accountId) {
        super("The account with id '"+accountId+"' has insufficient funds to perform the movement");
    }

}

