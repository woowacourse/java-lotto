package lotto.model.money;

public class Money {

    private static final int UNIT = 1000;
    public static final String AMOUNT_POSITIVE_MESSAGE = "금액은 0 이상이어야 합니다.";
    public static final String MONEY_DIVIDABLE_BY_UNIT_MESSAGE = "금액은 천원 단위여야 합니다.";

    private final int originalAmount;
    private int currentAmount;

    private Money(int amount) {
        validate(amount);
        originalAmount = amount;
        this.currentAmount = amount;
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
            throw new IllegalArgumentException(AMOUNT_POSITIVE_MESSAGE);
        }
    }

    private void validateUnit(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException(MONEY_DIVIDABLE_BY_UNIT_MESSAGE);
        }
    }

    public int countBuyable() {
        return this.currentAmount / UNIT;
    }

    public void decreaseByUnit() {
        this.currentAmount -=  UNIT;
    }

    public double getOriginalAmount() {
        return this.originalAmount;
    }
}
