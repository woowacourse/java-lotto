package lotto.domain.exceptions;

public class InvalidLottoBuyingMoneyException extends RuntimeException {
    public InvalidLottoBuyingMoneyException(String message) {
        super(message);
    }
}
