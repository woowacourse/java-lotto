package domain.lottonumber;

import java.util.HashMap;
import java.util.Map;

public class LottoNumberFactory {
    private static final Map<Integer, LottoNumber> cache = new HashMap<>();
    private static final String ERROR_BOUND_MESSAGE = "1부터 45 사이의 숫자만 입력 가능합니다.";
    private static final int LOTTO_UNDER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;

    static {
        for (int i = LOTTO_UNDER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    private static void validateBound(int number) {
        if (number < LOTTO_UNDER_BOUND || number > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException(ERROR_BOUND_MESSAGE);
        }
    }

    public static LottoNumber getInstance(int index) {
        validateBound(index);
        return cache.get(index);
    }
}
