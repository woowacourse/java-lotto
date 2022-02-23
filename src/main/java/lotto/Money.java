package lotto;

public class Money {

    private static final int MINIMUM_MONEY = 0;

    private final long money;

    public Money(long money) {
        validatePositive(money);
        this.money = money;
    }

    private void validatePositive(long money) {
        if (money < MINIMUM_MONEY) {
            throw new IllegalArgumentException("돈은 0이상이어야 한다.");
        }
    }
}
