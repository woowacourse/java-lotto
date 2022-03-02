package lotto.domain;

import java.util.Objects;

public final class ManualLottoCount {
    private static final String EXCEED_UPPER_BOUND_ERROR = "최대 구매 개수를 초과했습니다.";

    private final int value;

    private ManualLottoCount(int count) {
        value = count;
    }

    public static ManualLottoCount of(int count, int upperBoundCount) {
        if (upperBoundCount < count) {
            throw new IllegalArgumentException(EXCEED_UPPER_BOUND_ERROR);
        }
        return new ManualLottoCount(count);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManualLottoCount that = (ManualLottoCount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ManualLottoCount{" +
                "value=" + value +
                '}';
    }
}
