package lotto.domain;

public class InvalidSizeException extends RuntimeException {
    public InvalidSizeException() {
    }

    public InvalidSizeException(String message) {
        super(message);
    }
}
