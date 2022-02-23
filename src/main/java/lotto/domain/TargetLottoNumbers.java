package lotto.domain;

import lotto.domain.vo.LottoNumber;

public class TargetLottoNumbers {
    private final LottoNumbers targetLottoNumbers;
    private final LottoNumber bonusLottoNumber;

    public TargetLottoNumbers(final LottoNumbers targetLottoNumbers, final LottoNumber bonusLottoNumber) {
        this.targetLottoNumbers = targetLottoNumbers;
        this.bonusLottoNumber = bonusLottoNumber;
    }
}
