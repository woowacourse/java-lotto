package lotto.domain;

public class Amount {
    private static final String REQUEST_POSITIVE_NUMBER_MESSAGE = "양수를 입력해주세요.";

    private final int originalAmount;
    private int amount;

    public Amount(int amount) {
        checkAmountPositive(amount);
        this.amount = amount;
        this.originalAmount = amount;
    }

    private void checkAmountPositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(REQUEST_POSITIVE_NUMBER_MESSAGE);
        }
    }

    public int getAmountDividedBy(int amount) {
        return this.amount / amount;
    }

    public void decrease(int amount) {
        this.amount -= amount;
    }

    public boolean isPurchasableBy(int amount) {
        return amount <= this.amount;
    }

    public int getOriginalAmount() {
        return originalAmount;
    }
}
