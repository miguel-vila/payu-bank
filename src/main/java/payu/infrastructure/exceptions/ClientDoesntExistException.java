package payu.infrastructure.exceptions;

public class ClientDoesntExistException extends PayuBankException {

    public ClientDoesntExistException(Long clientId){
        super("The client with id "+clientId+" doesn't exist");
    }
}
