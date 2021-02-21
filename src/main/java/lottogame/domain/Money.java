package lottogame.domain;

public class Money {

    private int value;

    public Money(final int value) {
        this.value = value;
        validateNegative(this.value);
    }

    private void validateNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈 입력은 0 이상이어야 합니다.");
        }
    }

    public boolean canBuyAmount(final int amount) {
        return this.value >= amount;
    }

    public void spent(final int value) {
        this.value -= value;
    }

    public int getValue() {
        return this.value;
    }
}
