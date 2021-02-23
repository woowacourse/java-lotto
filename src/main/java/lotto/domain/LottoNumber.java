package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {

    public static final int MAX_NUMBER = 45;
    public static final int MIN_NUMBER = 1;
    public static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해 주세요.";
    private final int number;

    private static Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>();
    static {
        for (int i=MIN_NUMBER; i<=MAX_NUMBER; i++) {
            lottoNumberCache.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateRange(number);
        return lottoNumberCache.get(number);
    }

    public static LottoNumber of(String input) {
        int number = validateNumber(input);
        validateRange(number);
        return lottoNumberCache.get(number);
    }

    private static int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private static void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return Objects.equals(number, lottoNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
