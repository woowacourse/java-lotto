package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final String NUMBER_RANGE_ERROR = "로또 번호 범위가 벗어났습니다.";

    private final int number;

    private LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int lottoNumber) {
        if (lottoNumber < MINIMUM_NUMBER || lottoNumber > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    public static LottoNumber from(int number) {
        return CACHE.computeIfAbsent(number, LottoNumber::new);
    }

    public int getNumber() {
        return number;
    }
}
