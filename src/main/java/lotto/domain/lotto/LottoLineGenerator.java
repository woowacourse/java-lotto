package lotto.domain.lotto;

import static lotto.utils.Config.END;
import static lotto.utils.Config.START;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoLineGenerator {

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoLineGenerator() {
        for (int i = START; i <= END; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public LottoLine createLottoLine() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> picked = lottoNumbers.subList(0, 6);
        Collections.sort(picked);
        return new LottoLine(picked);
    }
}
