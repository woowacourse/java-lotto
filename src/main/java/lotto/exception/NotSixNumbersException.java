package lotto.exception;

public class NotSixNumbersException extends RuntimeException {
    public NotSixNumbersException() {
        super();
    }

    public NotSixNumbersException(String message) {
        super(message);
    }
}
