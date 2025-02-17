package lotto.model;

import java.util.HashSet;
import java.util.Set;

public class LottoNumberFixture {
     public static Set<LottoNumber> generateLottoNumbersInRange(int start, int end) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int i = start; i <= end; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return lottoNumbers;
    }
}
