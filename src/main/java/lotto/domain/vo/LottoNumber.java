package lotto.domain.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = new HashMap<>();

        for (int number = MINIMUM_NUMBER; number <= MAXIMUM_NUMBER; number++) {
            LOTTO_NUMBERS.put(number, new LottoNumber(number));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        validateRangeOfNumber(number);
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateRangeOfNumber(number);
        return LOTTO_NUMBERS.get(number);
    }

    public static List<LottoNumber> values() {
        return IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .mapToObj(LottoNumber::of)
            .collect(Collectors.toList());
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber)o;
        return this.number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    private static void validateRangeOfNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하이어야 한다.");
        }
    }
}
