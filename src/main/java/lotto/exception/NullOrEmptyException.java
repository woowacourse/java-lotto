package lotto.exception;

public class NullOrEmptyException extends RuntimeException {
    public NullOrEmptyException() {
        super();
    }

    public NullOrEmptyException(String message) {
        super(message);
    }
}
