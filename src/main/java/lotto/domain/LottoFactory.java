package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private static List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = new ArrayList<>();
        for (Integer number = 1; number <= 45; number++) {
            lottoNumbers.add(LottoNumber.createLottoNumber(number));
        }
    }
}
