package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.number.LottoNumber;

public class LottoGenerator {

    public LottoGenerator() {

    }

    public LottoGroup generateLotties(int count) {
        List<LottoNumbers> lotties = new ArrayList();

        for (int i = 0; i < count; i++) {
            LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6))
            );
            lotties.add(lottoNumbers);
        }

        return new LottoGroup(lotties);
    }
}
