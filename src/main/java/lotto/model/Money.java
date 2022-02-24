package lotto.model;

public class Money {

    private static final int UNIT = 1000;

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money of(int money) {
        return new Money(money);
    }

    private void validate(int amount) {
        validateUnit(amount);
        validatePositive(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("금액은 천원 단위여야 합니다.");
        }
    }

    public int count() {
        return this.amount / UNIT;
    }

    public int getAmount() {
        return this.amount;
    }
}
