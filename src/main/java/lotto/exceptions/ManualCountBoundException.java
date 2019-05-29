package lotto.exceptions;

public class ManualCountBoundException extends RuntimeException {
    public ManualCountBoundException() {
        super();
    }

    public ManualCountBoundException(String message) {
        super(message);
    }

    public ManualCountBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManualCountBoundException(Throwable cause) {
        super(cause);
    }

    protected ManualCountBoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
