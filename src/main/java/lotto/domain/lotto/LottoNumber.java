package lotto.domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_MATCHER;
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int number;

    static {
        LOTTO_NUMBER_MATCHER = new HashMap<>();

        for (int i = LottoNumber.MINIMUM_LOTTO_NUMBER; i <= LottoNumber.MAXIMUM_LOTTO_NUMBER; i++) {
            LottoNumber lottoNumber = new LottoNumber(i);
            LOTTO_NUMBER_MATCHER.put(i, lottoNumber);
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        Optional<LottoNumber> lottoNumber = Optional.ofNullable(LOTTO_NUMBER_MATCHER.get(number));
        return lottoNumber.orElseThrow(() -> new IllegalArgumentException("로또는 1부터 45까지의 숫자만 가능합니다."));
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
