package lotto.util;

import lotto.domain.Lotto;

public interface LottoGenerator {
    int LOTTO_COUNT = 6;
    int LOTTO_MINIMUM_NUMBER = 1;
    int LOTTO_MAXIMUM_NUMBER = 45;

    Lotto generate(String... varargs);

    boolean isNotMatchArgs();


}
