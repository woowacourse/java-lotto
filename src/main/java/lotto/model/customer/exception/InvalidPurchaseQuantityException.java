package lotto.model.customer.exception;

public class InvalidPurchaseQuantityException extends RuntimeException {
    public InvalidPurchaseQuantityException(String message) {
        super(message);
    }
}
