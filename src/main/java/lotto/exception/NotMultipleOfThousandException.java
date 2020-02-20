package lotto.exception;

public class NotMultipleOfThousandException extends RuntimeException {
    public NotMultipleOfThousandException () {
        super();
    }

    public NotMultipleOfThousandException (String message) {
        super(message);
    }
}
