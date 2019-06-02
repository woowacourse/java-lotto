package lotto.exceptions;

public class CountRangeException extends RuntimeException {
    public CountRangeException() {
    }

    public CountRangeException(String message) {
        super(message);
    }

    public CountRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountRangeException(Throwable cause) {
        super(cause);
    }

    public CountRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
