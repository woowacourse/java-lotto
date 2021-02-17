package lotto.domain.lotto;

import java.util.Objects;

public class LottoNumber {

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int value) {
        validateLottoNumber(value);
        return new LottoNumber(value);
    }

    private static void validateLottoNumber(int value) {
        if (value <= 0 || value > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이의 값이어야 합니다.");
        }
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
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
