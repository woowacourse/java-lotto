package lotto.exceptions;

public class NumberCountException extends IllegalArgumentException {
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
}
