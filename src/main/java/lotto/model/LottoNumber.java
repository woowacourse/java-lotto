package lotto.model;

import java.util.Objects;

public class LottoNumber {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        this.number = validateRange(number);
    }

    private int validateRange(final int number) {
        if (number < START_NUMBER || number > END_NUMBER) {
            throw new IllegalArgumentException(String.format("%d부터 %d 사이의 수를 입력해 주세요.", START_NUMBER, END_NUMBER));
        }
        return number;
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

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
