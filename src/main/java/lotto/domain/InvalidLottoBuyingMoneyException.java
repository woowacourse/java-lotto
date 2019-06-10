package lotto.domain;

public class InvalidLottoBuyingMoneyException extends RuntimeException {
    public InvalidLottoBuyingMoneyException(String message) {
        super(message);
    }
}
