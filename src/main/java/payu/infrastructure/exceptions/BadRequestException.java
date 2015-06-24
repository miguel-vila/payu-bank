package payu.infrastructure.exceptions;

public class BadRequestException extends PayuBankException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
