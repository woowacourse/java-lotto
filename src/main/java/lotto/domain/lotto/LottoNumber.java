package lotto.domain.lotto;

import java.util.*;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final Map<Integer, LottoNumber> numbers = new HashMap<>();

    private final int number;

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        return numbers.get(number);
    }

    public static List<LottoNumber> getAllLottoNumbers() {
        return new ArrayList<>(numbers.values());
    }
}
