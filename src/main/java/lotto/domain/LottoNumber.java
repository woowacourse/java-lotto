package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int MAX = 45;
    private static final int MIN = 1;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException("로또 숫자는 1~45 사이의 숫자여야 합니다.");
        }
    }

    private boolean isInLottoRange(int number) {
        return number <= MAX && number >= MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
