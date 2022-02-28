package lotto.model.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class LottoNumbers {
    private static final Set<Integer> lottoNumbers = new HashSet<>();
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    static {
        IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .forEach(lottoNumbers::add);
    }

    private LottoNumbers() {
    }

    public static Set<Integer> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
