package lotto.domain;

public class Money {
    private static final int MIN_MONEY = 0;
    private final int money;

    public Money(int money) {
        if (money < MIN_MONEY) {
            throw new InvalidMoneyException("돈은 적어도 " + MIN_MONEY + "이상이어야 합니다.");
        }
        this.money = money;
    }

    public Money add(Money another) {
        return new Money(money + another.money);
    }

    public int getValue() {
        return money;
    }
}
