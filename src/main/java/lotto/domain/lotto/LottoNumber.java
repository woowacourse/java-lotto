package lotto.domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHE_LOTTO_NUMBERS = new HashMap<>();

    static {
        IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
                .forEach(i -> CACHE_LOTTO_NUMBERS.put(i, new LottoNumber(i)));
    }

    private final int value;

    private LottoNumber(int number) {
        validateLottoNumber(number);
        this.value = number;
    }

    private static void validateLottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 범위를 초과했습니다.");
        }
    }

    public static LottoNumber valueOf(int index) {
        validateLottoNumber(index);
        return CACHE_LOTTO_NUMBERS.get(index);
    }

    public static Set<Integer> getKeys() {
        return CACHE_LOTTO_NUMBERS.keySet();
    }

    private int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return Integer.compare(this.getValue(), anotherLottoNumber.getValue());
    }
}
