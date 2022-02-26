package lotto.model.lotto;

import lotto.model.number.LottoNumbers;

public interface LottoGenerator {
    LottoNumbers generateLottoNumbers(int minimumNumber, int maximumNumber, int lottoLength);
}
