package lotto.domain.lotto;

import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_NUMBER_END;
import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_NUMBER_START;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoLineGenerator {

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoLineGenerator() {
        for (int i = LOTTO_NUMBER_START; i <= LOTTO_NUMBER_END; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public LottoLine createLottoLine() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> picked = lottoNumbers.subList(0, 6);
        return new LottoLine(picked);
    }

}
