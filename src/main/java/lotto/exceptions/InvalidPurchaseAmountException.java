package lotto.exceptions;

public class InvalidPurchaseAmountException extends RuntimeException {
    public InvalidPurchaseAmountException() {
    }

    public InvalidPurchaseAmountException(String message) {
        super(message);
    }

    public InvalidPurchaseAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPurchaseAmountException(Throwable cause) {
        super(cause);
    }

    public InvalidPurchaseAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
