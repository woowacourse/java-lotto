package lotto.domain.exceptions;

public class PurchaseAmountException extends RuntimeException {
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

    public PurchaseAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
