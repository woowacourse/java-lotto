package domain;

public class Money {
    private static final int ZERO = 0;

    private final int value;

    public Money(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int value) {
        if (value < ZERO) {
            throw new IllegalArgumentException("구입 금액은 0이상이어야 합니다.");
        }
    }

    public int divide(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("0이하의 값으로 나눌 수 없습니다.");
        }

        return this.value / value;
    }
}
