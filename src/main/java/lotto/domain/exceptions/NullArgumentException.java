package lotto.domain.exceptions;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException(String message) {
        super(message);
    }
}
