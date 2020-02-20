package lotto.exception;

public class NotSixNumbers extends RuntimeException {
    public NotSixNumbers () {
        super();
    }

    public NotSixNumbers (String message) {
        super(message);
    }
}
