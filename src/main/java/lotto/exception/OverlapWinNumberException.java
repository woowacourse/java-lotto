package lotto.exception;

public class OverlapWinNumberException extends RuntimeException {
    public OverlapWinNumberException () {
        super();
    }

    public OverlapWinNumberException (String message) {
        super(message);
    }
}
