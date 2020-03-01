package lotto.domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static String WRONG_LOTTO_NUMBER = "로또 숫자는 1부터 45까지의 숫자만 가능합니다.";
    private static Map<Integer, LottoNumber> lottoNumberMap;
    static final int MINIMUM_LOTTO_NUMBER = 1;
    static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int number;

    static {
        lottoNumberMap = new HashMap<>();

        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            lottoNumberMap.put(i, new LottoNumber(i));
        }
    }

    public static LottoNumber publishLottoNumber(int number) {
        validateLottoNumber(number);
        return lottoNumberMap.get(number);
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    private static void validateLottoNumber(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(WRONG_LOTTO_NUMBER);
        }
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
