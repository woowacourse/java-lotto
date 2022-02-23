package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    public static final int MIN = 1;
    public static final int MAX = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException(String.format("로또 숫자는 %d~%d 사이의 숫자여야 합니다.", MIN, MAX));
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

    public int getNumber() {
        return number;
    }
}
