package lotto.domain.lotto;

public class InvalidSizeException extends RuntimeException {
    public InvalidSizeException() {
    }

    public InvalidSizeException(String message) {
        super(message);
    }
}
