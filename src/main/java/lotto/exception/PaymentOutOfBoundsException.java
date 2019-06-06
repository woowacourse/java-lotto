package lotto.exception;

public class PaymentOutOfBoundsException extends IllegalArgumentException {
    public PaymentOutOfBoundsException(String message) {
        super(message);
    }
}
