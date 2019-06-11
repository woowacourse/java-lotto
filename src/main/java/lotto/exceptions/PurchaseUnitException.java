package lotto.exceptions;

public class PurchaseUnitException extends IllegalArgumentException {
    public PurchaseUnitException() {
    }

    public PurchaseUnitException(String message) {
        super(message);
    }

    public PurchaseUnitException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseUnitException(Throwable cause) {
        super(cause);
    }
}
