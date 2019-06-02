package lotto.exceptions;

public class PurchaseUnitException extends RuntimeException {
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

    public PurchaseUnitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
