package lotto.view;

public class IllegalUserInputException extends RuntimeException {
    public IllegalUserInputException() {
        super();
    }

    public IllegalUserInputException(final String message) {
        super(message);
    }
}
