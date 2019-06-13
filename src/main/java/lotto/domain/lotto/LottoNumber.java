package lotto.domain.lotto;

import lotto.exception.InvalidLottoNumbersException;

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
        LottoNumber value = numbers.get(number);
        checkNumberIn(value);
        return value;
    }

    private static void checkNumberIn(LottoNumber value) {
        if (value == null) {
            throw new InvalidLottoNumbersException("로또에 사용되는 숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

    public static List<LottoNumber> getAllLottoNumbers() {
        return new ArrayList<>(numbers.values());
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
