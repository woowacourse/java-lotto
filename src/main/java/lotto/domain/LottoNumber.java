package lotto.domain;

import lotto.exception.IllegalNumberBoundException;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
    private static final int FIRST_BOUND_OF_LOTTO_NUMBER = 1;
    private static final int LAST_BOUND_OF_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> numbers = new HashMap<>();

    private final int number;

    static {
        for (int i = FIRST_BOUND_OF_LOTTO_NUMBER; i <= LAST_BOUND_OF_LOTTO_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    public LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber getNumber(int key) throws IllegalNumberBoundException {
        validateNumberBound(key);

        return numbers.get(key);
    }

    private static void validateNumberBound(int key) {
        if (key < FIRST_BOUND_OF_LOTTO_NUMBER || key >= LAST_BOUND_OF_LOTTO_NUMBER) {
            throw new IllegalNumberBoundException("로또 번호의 범위에 벗어납니다.");
        }
    }

}
