package lotto.model;

import static lotto.model.LottoNumber.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.model.LottoNumber.LOTTO_NUMBER_UPPER_BOUND;

public interface LottoGenerator {

    Lotto createLotto();

    static LottoGenerator randomLottoGenerator() {
        return new RandomLottoGenerator(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND);
    }
}
