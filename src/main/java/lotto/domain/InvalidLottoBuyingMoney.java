package lotto.domain;

public class InvalidLottoBuyingMoney extends RuntimeException {
    public InvalidLottoBuyingMoney(String message) {
        super(message);
    }
}
