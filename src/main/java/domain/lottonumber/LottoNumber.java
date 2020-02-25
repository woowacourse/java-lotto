package domain.lottonumber;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 46;

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(LottoNumber number) {
        return this.number - number.number;
    }

    public static List<LottoNumber> getAllValues() {
        return NumberCache.cache;
    }

    public static LottoNumber valueOf(int number) {
        return NumberCache.cache.stream()
                .filter(cache -> cache.getValue() == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("1부터 45까지의 숫자만 가능합니다."));
    }

    public int getValue() {
        return this.number;
    }

    private static class NumberCache {
        static final List<LottoNumber> cache;

        static {
            cache = new ArrayList<>();
            for (int i = MIN_BOUND; i < MAX_BOUND; i++) {
                cache.add(new LottoNumber(i));
            }
        }
    }
}
