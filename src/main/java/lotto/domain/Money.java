package lotto.domain;

public class Money {

    private final int amount;

    public Money(final int amount) {
        checkLowerThanStandard(amount);
        this.amount = amount;
    }

    private void checkLowerThanStandard(final int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("");
        }
    }

    public int calculateLottoCount() {
        return amount / 1000;
    }
}
