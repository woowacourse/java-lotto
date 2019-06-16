package lotto.domain.exception;

public class ExceedBoughtLottosAboutMoneyException extends RuntimeException {
    public ExceedBoughtLottosAboutMoneyException(final String message) {
        super(message);
    }
}
