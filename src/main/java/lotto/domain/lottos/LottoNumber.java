package lotto.domain.lottos;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_NUMBER_RANGE = 1;
    public static final int MAX_NUMBER_RANGE = 45;
    public static final String RANGE_ERROR_MESSAGE = "숫자는 %d ~ %d 사이의 숫자여야한다.";

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validateRange(int number) {
        if (number >= MIN_NUMBER_RANGE && number <= MAX_NUMBER_RANGE) {
            return;
        }
        throw new IllegalArgumentException(String.format(RANGE_ERROR_MESSAGE, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE));
    }

    @Override
    public boolean equals(Object o) {
        return number == ((LottoNumber) o).number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(number, lottoNumber.number);
    }
}
