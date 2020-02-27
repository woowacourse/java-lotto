package lotto.domain.exception;

public class InvalidInputSizeException extends IllegalArgumentException{
    private static final String INVALID_INPUT_SIZE_EXCEPTION_DEFAULT_MESSAGE = "Lotto number amount must be 6.";

    public InvalidInputSizeException() {
        super(INVALID_INPUT_SIZE_EXCEPTION_DEFAULT_MESSAGE);
    }

    public InvalidInputSizeException(String message) {
        super(message);
    }
}