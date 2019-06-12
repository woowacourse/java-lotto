package lotto.domain.exception;

public class NumberDuplicationException extends RuntimeException {
    public NumberDuplicationException(String message) {
        super(message);
    }
}
