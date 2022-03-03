package lotto.domain.lotto;

public class Count {

    private final int value;

    public Count(int value) {
        validateCount(value);
        this.value = value;
    }

    public Count subtract(Count count) {
        validateSubtract(count);
        return new Count(value - count.value);
    }

    public int value() {
        return value;
    }

    private void validateCount(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("0 이상의 값이어야 합니다.");
        }
    }

    private void validateSubtract(Count count) {
        if (value - count.value < 0) {
            throw new IllegalArgumentException(String.format("%d보다 작은 값이어야 합니다.", value));
        }
    }
}
