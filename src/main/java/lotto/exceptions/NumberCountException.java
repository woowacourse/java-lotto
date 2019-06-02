package lotto.exceptions;

public class NumberCountException extends RuntimeException {
    public NumberCountException() {
        super();
    }

    public NumberCountException(String message) {
        super(message);
    }

    public NumberCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberCountException(Throwable cause) {
        super(cause);
    }

    protected NumberCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
