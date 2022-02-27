package lotto.domain;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE;

    private final int number;

    static {
        LOTTO_NUMBER_CACHE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toMap(number -> number, LottoNumber::new));
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        checkLottoNumberRange(number);
        return LOTTO_NUMBER_CACHE.get(number);
    }

    private static void checkLottoNumberRange(final int number) {
        if (!LOTTO_NUMBER_CACHE.containsKey(number)) {
            throw new IllegalArgumentException("[ERROR] 입력값이 1 이상 45 이하여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
