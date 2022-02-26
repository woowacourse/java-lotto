package lotto.domain;

public class Money {
    private static final String ERROR_ONLY_NATURAL_NUMBER = "금액은 음수가 될 수 없습니다.";

    private final int money;

    public Money(final int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException(ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    public int getMoney() {
        return money;
    }
}
