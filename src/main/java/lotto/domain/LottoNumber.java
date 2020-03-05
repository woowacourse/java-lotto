package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.exception.InvalidLottoNumberException;

public class LottoNumber {
    private static final int MAX = 45;
    private static final int MIN = 1;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();
    private int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    private static void validateValue(int value) {
        if (value < MIN || MAX < value) {
            throw new InvalidLottoNumberException("유효하지 않은 로또 번호입니다");
        }
    }

    public static LottoNumber of(int value) {
        validateValue(value);
        if (CACHE.get(value) == null) {
            createCache();
        }
        return CACHE.get(value);
    }

    private static void createCache() {
        List<LottoNumber> lottoNumberInBox = createLottoNumberBox();
        for (int i = MIN; i < MAX + 1; i++) {
            CACHE.put(i, lottoNumberInBox.get(i - 1));
        }
    }

    public static List<LottoNumber> createLottoNumberBox() {
        return IntStream.rangeClosed(MIN, MAX)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LottoNumber))
            return false;
        LottoNumber that = (LottoNumber)o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
