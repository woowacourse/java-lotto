package lottogame.domain.number;

import java.util.Objects;

public class LottoNumber {

    private static final int START_NUMBER = 1;
    private static final int FINISH_NUMBER = 45;

    private final int value;

    public LottoNumber(final String value) {
        this(Integer.parseInt(value));
    }

    public LottoNumber(final int value){
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value < START_NUMBER || FINISH_NUMBER < value) {
            throw new IllegalArgumentException("로또 숫자 범위를 벗어났습니다.");
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
        LottoNumber lottoNumber = (LottoNumber) o;
        return value == lottoNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
