package lottogame.domain;

public class Money {

    private int value;

    public Money(final String money) {
        this.value = Integer.parseInt(money);
        validateNegative(this.value);
    }

    private void validateNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈 입력은 양수이여야 합니다.");
        }
    }

    public boolean isCanBuyAmount(final int amount) {
        return this.value >= amount;
    }

    public void spent(final int value) {
        this.value -= value;
    }

    public int getValue() {
        return this.value;
    }
}
