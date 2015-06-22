package payu.infrastructure.exceptions;

public class AccountDoesntExistException extends PayuBankException {
    public AccountDoesntExistException(Long accountId) {
        super("There is no account with id "+accountId);
    }
}
