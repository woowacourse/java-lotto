package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
    protected static final int MIN = 1;
    protected static final int MAX = 45;

    private static final String NUMBER_RANGE_ERROR = String.format("로또 숫자는 %d 이상 %d 이하의 숫자만 가능합니다.", MIN, MAX);

    private static final Map<Integer, LottoNumber> cacheLottoNumber = new HashMap<>();

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber of(int number) {
        validateNumber(number);
        return generateLottoNumber(number);
    }

    private static void validateNumber(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private static LottoNumber generateLottoNumber(int number) {
        LottoNumber lottoNumber = cacheLottoNumber.get(number);
        if (lottoNumber == null) {
            lottoNumber = new LottoNumber(number);
            cacheLottoNumber.put(number, lottoNumber);
        }
        return lottoNumber;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + value +
                '}';
    }
}
