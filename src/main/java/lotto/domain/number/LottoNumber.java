package lotto.domain.number;

import java.util.Objects;

public class LottoNumber {

    private static final Number NUMBER_MINIMUM = Number.valueOf(1);
    private static final Number NUMBER_MAXIMUM = Number.valueOf(45);

    private final Number number;

    private LottoNumber(Number number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        return new LottoNumber(Number.valueOf(number));
    }

    public static LottoNumber valueOf(String number) {
        return new LottoNumber(Number.valueOf(number));
    }

    private static void validateRange(Number number) {
        if (NUMBER_MINIMUM.isBiggerThan(number) || number.isBiggerThan(NUMBER_MAXIMUM)) {
            throw new IllegalArgumentException("범위 밖의 로또 번호 입니다.");
        }
    }

    public int toInt() {
        return number.toInt();
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
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number.toInt());
    }
}