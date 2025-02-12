package model;

import constant.ErrorMessage;
import java.util.List;

public class WinLotto {
    private final LottoNumbers lottoNumbers;
    private final Integer bonusNumber;

    public WinLotto(List<Integer> winNumbers, Integer bonusNumber) {
        this.lottoNumbers = new LottoNumbers(winNumbers);
        validateDuplicate(winNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(List<Integer> winNumbers, Integer bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATE_EXCEPTION);
        }
    }
}
