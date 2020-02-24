package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberBox {
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int ONE = 1;
    private static final List<LottoNumber> LOTTO_NUMBERS = IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER + ONE)
        .mapToObj(LottoNumber::new)
        .collect(Collectors.toList());

    static List<LottoNumber> get() {
        return LOTTO_NUMBERS;
    }

}
