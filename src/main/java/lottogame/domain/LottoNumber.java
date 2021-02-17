package lottogame.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int START_NUMBER = 1;
    private static final int FINISH_NUMBER = 45;

    private final int value;

    public LottoNumber(final String value) {
        this.value = Integer.parseInt(value);
        validateRange(this.value);
    }

    private void validateRange(final int value) {
        if (value < START_NUMBER || FINISH_NUMBER < value) {
            throw new IllegalArgumentException("로또 숫자 범위를 벗어났습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return value == lottoNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
