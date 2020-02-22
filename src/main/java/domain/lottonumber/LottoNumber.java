package domain.lottonumber;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int DUMMY_VALUE = -1;
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 46;

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    private static void validateNumber(int number) {
        if (number == DUMMY_VALUE) {
            return;
        }

        if (number < MIN_BOUND || number >= MAX_BOUND) {
            throw new IllegalArgumentException("1부터 45까지의 숫자만 가능합니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber number) {
        return this.number - number.number;
    }

    public static List<LottoNumber> getAllValues() {
        return NumberCache.cache;
    }

    public static LottoNumber valueOf(int number) {
        validateNumber(number);

        return NumberCache.cache.stream()
                .filter(a -> a.getValue() == number)
                .findAny()
                .orElse(NumberCache.dummyNumber);
    }

    public int getValue() {
        return this.number;
    }

    private static class NumberCache {
        static final List<LottoNumber> cache;
        static final LottoNumber dummyNumber;

        static {
            cache = new ArrayList<>();
            for (int i = MIN_BOUND; i < MAX_BOUND; i++) {
                cache.add(new LottoNumber(i));
            }

            dummyNumber = new LottoNumber(DUMMY_VALUE);
        }
    }
}
