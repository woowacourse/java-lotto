package lotto.domain.exception;

public class NotMatchLottoPrizeException extends RuntimeException {
    public NotMatchLottoPrizeException(String message) {
        super(message);
    }
}
