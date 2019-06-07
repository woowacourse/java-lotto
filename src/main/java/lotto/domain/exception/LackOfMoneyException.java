package lotto.domain.exception;

public class LackOfMoneyException extends RuntimeException {
    public LackOfMoneyException(final String message) {
        super(message);
    }
}
