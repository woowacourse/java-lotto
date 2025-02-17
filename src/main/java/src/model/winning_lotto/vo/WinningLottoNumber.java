package src.model.winning_lotto.vo;

import java.util.Objects;

public class WinningLottoNumber {

    protected static final int MIN_NUMBER_RANGE = 1;
    protected static final int MAX_NUMBER_RANGE = 45;

    private final int value;

    private WinningLottoNumber(final int value) {
        validate(value);
        this.value = value;
    }

    public static WinningLottoNumber from(final int value) {
        return new WinningLottoNumber(value);
    }

    private static void validate(final int value) {
        if (value < MIN_NUMBER_RANGE || value > MAX_NUMBER_RANGE) {
            throw new IllegalArgumentException("당첨 로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningLottoNumber that = (WinningLottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "WinningLottoNumber{" +
                "value=" + value +
                '}';
    }
}
