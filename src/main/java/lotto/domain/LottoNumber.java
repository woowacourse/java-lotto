package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class LottoNumber {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private static final Map<Integer, LottoNumber> cache = new HashMap<>(MAXIMUM_NUMBER);

    private final int number;

    static {
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(String numberValue) {
        return valueOf(convertToLottoNumber(numberValue));
    }

    public static LottoNumber valueOf(int i) {
        if (i <= MAXIMUM_NUMBER && i >= MINIMUM_NUMBER) {
            return cache.get(i);
        }
        return new LottoNumber(i);
    }

    private static int convertToLottoNumber(String numberValue) {
        numberValue = numberValue.trim();
        validateIsNumber(numberValue);
        return Integer.parseInt(numberValue);
    }

    private static void validateIsNumber(String numberValue) {
        if (!StringChecker.isNumber(numberValue)) {
            throw new CustomException("로또 넘버는 숫자이어야 합니다.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < MINIMUM_NUMBER || MAXIMUM_NUMBER < number) {
            throw new CustomException("로또 넘버는 1~45 사이 정수이어야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
