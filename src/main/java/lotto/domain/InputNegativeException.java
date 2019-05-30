package lotto.domain;

public class InputNegativeException extends RuntimeException {
    public InputNegativeException() {
    }

    public InputNegativeException(String message) {
        super(message);
    }
}