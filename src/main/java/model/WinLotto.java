package model;

import constant.ErrorMessage;
import java.util.List;

public class WinLotto {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinLotto(List<Integer> winNumbers, Integer bonusNumber) {
        validateDuplicate(winNumbers, bonusNumber);
        this.lottoNumbers = new LottoNumbers(winNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public Integer countMatchNumber(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.countMatchNumber(lottoNumbers);
    }

    public Boolean bonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.bonusMatch(this.bonusNumber);
    }

    private void validateDuplicate(List<Integer> winNumbers, Integer bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATE_EXCEPTION);
        }
    }
}
