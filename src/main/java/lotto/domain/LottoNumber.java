package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private final int number;

    public LottoNumber(int value) throws IllegalArgumentException {
        validateRange(value);
        this.number = value;
    }

    public int getNumber() {
        return this.number;
    }

    private void validateRange(int value) throws IllegalArgumentException {
        if (value < MINIMUM || MAXIMUM < value) {
            throw new IllegalArgumentException("로또 번호는 1이상, 45이하여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
