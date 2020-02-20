package lotto.exception;

public class NotNumberException extends RuntimeException {
    public NotNumberException() {
        super();
    }
    public NotNumberException(String message) {
        super(message);
    }
}
