package lotto.domain;

public class Money {

    private final int amount;

    public Money(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    public static Money wons(int amount) {
        return new Money(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
    }

    public Money divide(Money other) {
        return Money.wons(this.amount / other.amount);
    }

    public boolean isGreatThanOrEqualTo(Money other) {
        return this.amount >= other.amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
