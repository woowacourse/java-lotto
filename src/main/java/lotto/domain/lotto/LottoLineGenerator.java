package lotto.domain.lotto;

import static lotto.domain.lotto.LottoNumber.LOTTO_NUMBER_END;
import static lotto.domain.lotto.LottoNumber.LOTTO_NUMBER_START;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoLineGenerator {

    private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>();
    private static final int PICKED_LOTTO_NUMBERS_FROM = 0;
    private static final int PICKED_LOTTO_NUMBERS_TO = 6;

    public LottoLineGenerator() {
        for (int i = LOTTO_NUMBER_START; i <= LOTTO_NUMBER_END; i++) {
            LOTTO_NUMBERS.add(new LottoNumber(i));
        }
    }

    public LottoLine createLottoLine() {
        Collections.shuffle(LOTTO_NUMBERS);
        List<LottoNumber> picked = LOTTO_NUMBERS
            .subList(PICKED_LOTTO_NUMBERS_FROM, PICKED_LOTTO_NUMBERS_TO);
        return new LottoLine(picked);
    }

}
