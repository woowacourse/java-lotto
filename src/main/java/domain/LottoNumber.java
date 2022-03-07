package domain;

import static utils.Messages.LOTTO_NUMBER_RANGE_ERROR_MESSAGE;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int number;

    static {
        for (int i = LOTTO_NUMBER_LOWER_BOUND; i <= LOTTO_NUMBER_UPPER_BOUND; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        if (CACHE.containsKey(number)) {
            return CACHE.get(number);
        }
        throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
    }

    public static Collection<LottoNumber> values() {
        return CACHE.values();
    }

    public int get() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}