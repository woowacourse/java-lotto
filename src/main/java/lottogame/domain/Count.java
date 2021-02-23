package lottogame.domain;

import java.util.Objects;

public class Count {

    private int value;

    public Count(final Count count) {
        this(count.value);
    }

    public Count(final int value) {
        this.value = value;
        validateNegative(this.value);
    }

    private void validateNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("개수 입력은 0 이상이어야 합니다.");
        }
    }

    public boolean isRemain() {
        return this.value > 0;
    }

    public void reduce() {
        this.value -= 1;
    }

    public int multiplyWith(final int value) {
        return this.value * value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Count count = (Count) o;
        return value == count.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
