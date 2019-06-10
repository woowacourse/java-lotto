package lotto.domain.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(final String message) {
        super(message);
    }
}
