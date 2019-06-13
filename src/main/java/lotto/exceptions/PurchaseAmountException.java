package lotto.exceptions;

public class PurchaseAmountException extends IllegalArgumentException {
    public PurchaseAmountException() {
    }

    public PurchaseAmountException(String message) {
        super(message);
    }

    public PurchaseAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseAmountException(Throwable cause) {
        super(cause);
    }
}
