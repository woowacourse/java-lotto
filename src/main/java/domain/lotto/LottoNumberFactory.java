package domain.lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoNumberFactory {
    private static final int LOTTO_UNDER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final Map<Integer, LottoNumber> cache = new HashMap<>();

    static {
        for (int i = LOTTO_UNDER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    public static LottoNumber getInstance(int number) {
        if (number >= LOTTO_UNDER_BOUND && number <= LOTTO_UPPER_BOUND) {
            return cache.get(number);
        }
        return new LottoNumber(number);
    }
}
