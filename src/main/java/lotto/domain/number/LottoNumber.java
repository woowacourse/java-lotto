package lotto.domain.number;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 45;

    private static final String WRONG_RANGE_MSG_FORMAT = "로또 번호가 잘 못 되었습니다. 최소값 : %d, 최대값 : %d, 입력값 : %d";

    private final int number;

    private LottoNumber(int number) {
        validateLottoNumber(number);
        this.number = number;
    }

    public static LottoNumber createLottoNumber(int number) {
        return new LottoNumber(number);
    }

    private void validateLottoNumber(int number) {
        if (number > MAX_RANGE || number < MIN_RANGE) {
            throw new IllegalArgumentException(
                    String.format(WRONG_RANGE_MSG_FORMAT, LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE, number)
            );
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

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
