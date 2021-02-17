package lottogame.domain;

public class Money {

    private int value;

    public Money(final String money) {
        this.value = Integer.parseInt(money);
        isPositive(this.value);
    }

    public void use(final int price) {
        this.value -= price;
    }

    public int getValue() {
        return this.value;
    }

    private void isPositive(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈 입력은 양수이여야 합니다.");
        }
    }
}
