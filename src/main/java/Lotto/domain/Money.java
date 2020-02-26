package Lotto.domain;

public class Money {
    private static final int MIN_MONEY_AMOUNT = 0;

    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if(isNegativeNumber(money)) {
            throw new IllegalArgumentException("구입 금액은 0보다 커야합니다.");
        }
    }

    private boolean isNegativeNumber(int money) {
        return money <= MIN_MONEY_AMOUNT;
    }
}
