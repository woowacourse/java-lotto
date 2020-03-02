package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String WRONG_LOTTO_NUMBER = "로또 숫자는 1부터 45까지의 숫자만 가능합니다.";
    private static Map<Integer, LottoNumber> valueToLottoNumber;
    static final int MINIMUM_LOTTO_NUMBER = 1;
    static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int number;

    static {
        Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }

        valueToLottoNumber = Collections.unmodifiableMap(lottoNumbers);
    }

    public static LottoNumber publishLottoNumber(int number) {
        validateLottoNumber(number);
        return valueToLottoNumber.get(number);
    }

    private static void validateLottoNumber(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(WRONG_LOTTO_NUMBER);
        }
    }

    public static List<Integer> getLottoNumbers() {
        return new ArrayList<>(valueToLottoNumber.keySet());

    }

    private LottoNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber targetNumber) {
        return this.number - targetNumber.number;
    }
}
