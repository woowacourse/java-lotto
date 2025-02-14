package lotto.model.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> NUMBERS = new HashMap<>();

    private final int value;

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> NUMBERS.put(number, new LottoNumber(number)));
    }

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber draw(final int number) {
        validateNumberRange(number);
        return NUMBERS.get(number);
    }

    private static void validateNumberRange(final int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또는 %d 이상 %d 이하만 가능합니다."
                    .formatted(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber lottoNumber)) {
            return false;
        }
        return value == lottoNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public int compareTo(final LottoNumber o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Number{" +
                "value=" + value +
                '}';
    }

}
