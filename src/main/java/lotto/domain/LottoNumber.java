package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.exception.LottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;
    private static final List<LottoNumber> LOTTO_NUMBER_CACHE = new ArrayList<>();

    static {
        for (int i = MINIMUM_RANGE; i <= MAXIMUM_RANGE; i++) {
            LOTTO_NUMBER_CACHE.add(new LottoNumber(i));
        }
    }

    private final int lottoNumber;

    public LottoNumber(int number) {
        checkRange(number);
        this.lottoNumber = number;
    }

    public static LottoNumber getByNumber(int number) {
        checkRange(number);
        return LOTTO_NUMBER_CACHE.get(number - 1);
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
        return Collections.unmodifiableList(LOTTO_NUMBER_CACHE);
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.lottoNumber);
    }
}
