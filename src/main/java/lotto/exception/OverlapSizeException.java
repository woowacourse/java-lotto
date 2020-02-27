package lotto.exception;

public class OverlapSizeException extends RuntimeException {
    public OverlapSizeException () {
        super();
    }

    public OverlapSizeException (String message) {
        super(message);
    }
}
