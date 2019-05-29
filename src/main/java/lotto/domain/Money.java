package lotto.domain;

public class Money {
    private static final int MIN_MONEY = 0;
    private final int money;

    public Money(int money) {
        if (money < MIN_MONEY) {
            throw new InvalidMoneyException("돈은 적어도 0 이상이어야 합니다.");
        }
        this.money = money;
    }

    public boolean isMultipleOf(Money another) {
        return money % another.money == 0;
    }

    public int divideBy(Money another) {
        if (another.money == 0) {
            throw new ArithmeticException("0 으로 나눌 수 없습니다.");
        }
        return money / another.money;
    }

    public Money add(Money another) {
        return new Money(money + another.money);
    }
}
