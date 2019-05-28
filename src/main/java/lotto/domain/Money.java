package lotto.domain;

public class Money {
    private static final int MIN_MONEY = 1;
    private final int money;

    public Money(int money) {
        if (money < MIN_MONEY) {
            throw new InvalidMoney("돈은 적어도 0보다 커야합니다.");
        }
        this.money = money;
    }

    public boolean isMultipleOf(Money another) {
        return money % another.money == 0;
    }
}
