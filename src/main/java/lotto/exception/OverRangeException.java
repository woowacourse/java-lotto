package lotto.exception;

public class OverRangeException extends RuntimeException {
    public OverRangeException () {
        super();
    }

    public OverRangeException (String message) {
        super(message);
    }
}
