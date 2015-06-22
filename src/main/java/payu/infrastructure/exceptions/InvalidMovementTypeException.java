package payu.infrastructure.exceptions;

public class InvalidMovementTypeException extends PayuBankException {

    public InvalidMovementTypeException(String invalidType){
        super("'"+invalidType+"' is not a valid movement type");
    }
}
