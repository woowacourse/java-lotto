package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;

    private final int number;

    public LottoNumber(int number) {
        this.number = number;
        validateRange(number);
    }

    private void validateRange(int number) {
        if (number < MINIMUM || MAXIMUM < number) {
            throw new IllegalArgumentException("로또 번호는 1이상, 45이하여야 합니다.");
        }
    }

    public int getNumber() {
        return this.number;
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
