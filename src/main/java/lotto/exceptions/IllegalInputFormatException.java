package lotto.exceptions;

public class IllegalInputFormatException extends RuntimeException {
    public IllegalInputFormatException() {
        super();
    }

    public IllegalInputFormatException(String message) {
        super(message);
    }

    public IllegalInputFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputFormatException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
