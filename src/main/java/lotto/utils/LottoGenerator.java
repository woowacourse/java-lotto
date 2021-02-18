package lotto.utils;

import lotto.domain.Lotto;

public interface LottoGenerator {
    int MIN_LOTTO_NUMBER = 1;
    int MAX_LOTTO_NUMBER = 45;
    int FROM_INDEX = 0;

    Lotto generate();
}
