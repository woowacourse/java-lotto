package lotto.domain.vo;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final List<LottoNumber> LOTTO_TOTAL_NUMBERS = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
            .mapToObj(LottoNumber::new)
            .collect(toList());

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber getInstance(int number) {
        validateNumberBoundary(number);

        LottoNumber lottoNumber = LOTTO_TOTAL_NUMBERS.get(number - 1);

        return Objects.requireNonNullElseGet(lottoNumber, () -> new LottoNumber(number));
    }

    private static void validateNumberBoundary(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException("1~45의 숫자이어야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    public static List<LottoNumber> getLottoTotalNumbers() {
        return LOTTO_TOTAL_NUMBERS;
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.getNumber();
    }
}
