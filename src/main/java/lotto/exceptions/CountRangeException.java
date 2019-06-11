package lotto.exceptions;

public class CountRangeException extends IllegalArgumentException {
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
}
