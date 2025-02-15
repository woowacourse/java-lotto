package model;

import constant.ErrorMessage;
import java.util.List;

public class WinLotto {
    private final LottoNumbers lottoNumbers;
    private final Integer bonusNumber;

    public WinLotto(List<Integer> winNumbers, Integer bonusNumber) {
        validateDuplicate(winNumbers, bonusNumber);
        validateBound(bonusNumber);
        this.lottoNumbers = new LottoNumbers(winNumbers);
        this.bonusNumber = bonusNumber;
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

    private void validateBound(Integer input) {
        if (input < LottoNumbers.MINIMUM_LOTTO_NUMBER || input > LottoNumbers.MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_BOUND_EXCEPTION);
        }
    }
}
