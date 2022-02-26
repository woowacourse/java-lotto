package lotto.model.lotto.generator;

import lotto.model.number.LottoNumbers;

public interface LottoGenerator {
    LottoNumbers generateLottoNumbers(int minimumNumber, int maximumNumber, int lottoLength);
}
