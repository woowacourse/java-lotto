package lotto.model.customer.exception;

public class InvalidPurchaseAmountException extends RuntimeException {
    public InvalidPurchaseAmountException(String message) {
        super(message);
    }
}
