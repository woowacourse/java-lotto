package lotto.domain.number;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int NUMBER_START_EXCLUSIVE = 0;
    private static final int NUMBER_END_INCLUSIVE = 45;
    private static final List<LottoNumber> LOTTO_NUMBERS_CACHE;

    private final Number number;

    static {
        LOTTO_NUMBERS_CACHE = IntStream.rangeClosed(NUMBER_START_EXCLUSIVE, NUMBER_END_INCLUSIVE)
            .mapToObj(Number::valueOf)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private LottoNumber(Number number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validateRange(Number.valueOf(number));
        return LOTTO_NUMBERS_CACHE.get(number);
    }

    public static LottoNumber valueOf(String number) {
        validateRange(Number.valueOf(number));
        return new LottoNumber(Number.valueOf(number));
    }

    private static void validateRange(Number number) {
        if (!number.isBiggerThan(NUMBER_START_EXCLUSIVE) || number.isBiggerThan(NUMBER_END_INCLUSIVE)) {
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