package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.Arrays;

public interface LottoGenerator {
    int MIN_LOTTO_NUMBER = 1;
    int MAX_LOTTO_NUMBER = 45;
    int FROM_INDEX = 0;

    Lotto generate();

    default Lotto generateWinningLottoNumber() {
        return new Lotto(Arrays.asList(
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(9)
        ));
    }
}
