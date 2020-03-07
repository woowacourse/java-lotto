package lotto.domain.exception;

public class OutOfRangeException extends IllegalArgumentException {
    public OutOfRangeException(String message) {
        super(message);
    }
}