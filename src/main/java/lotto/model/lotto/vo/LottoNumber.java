package lotto.model.lotto.vo;

import java.util.Objects;

public class LottoNumber {

    protected static final int MIN = 1;
    protected static final int MAX = 45;

    private final int value;

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber from(final int value) {
        validate(value);
        return new LottoNumber(value);
    }

    private static void validate(final int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("로또 번호는 " + MIN + "부터 " + MAX + " 사이여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "value=" + value +
                '}';
    }
}
