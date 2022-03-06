package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.exception.LottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    static {
        for (int i = MINIMUM_RANGE; i <= MAXIMUM_RANGE; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    private final int lottoNumber;

    private LottoNumber(int number) {
        checkRange(number);
        this.lottoNumber = number;
    }

    public static LottoNumber from(int number) {
        checkRange(number);
        return CACHE.get(number);
    }

    private static void checkRange(int number) {
        if (!isCorrectRange(number)) {
            throw new LottoNumberException(LottoNumberException.LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectRange(int number) {
        return number >= MINIMUM_RANGE && number <= MAXIMUM_RANGE;
    }

    public static List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(CACHE.values());
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.lottoNumber);
    }
}
