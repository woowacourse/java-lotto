package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.utils.IntegerUtils;

public class LottoNumber {

    public static final int MIN = 1;
    public static final int MAX = 45;

    private static final Map<Integer, LottoNumber> NUMBER_CACHE =
        IntStream.rangeClosed(MIN, MAX)
            .boxed()
            .collect(Collectors.toUnmodifiableMap(Function.identity(), LottoNumber::new));


    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validate(number);
        return NUMBER_CACHE.get(number);
    }

    public static LottoNumber from(String input) {
        return from(IntegerUtils.parse(input));
    }

    private static void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException(String.format("로또 숫자는 %d~%d 사이의 숫자여야 합니다.", MIN, MAX));
        }
    }

    private static boolean isInLottoRange(int number) {
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
