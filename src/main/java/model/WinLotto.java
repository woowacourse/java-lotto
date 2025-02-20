package model;

import static view.ResultView.BONUS_NUMBER_DUPLICATE_EXCEPTION;

import java.util.List;

public class WinLotto {
    private final LottoNumbers lottoNumbers;
    private final int bonusNumber;

    public WinLotto(List<Integer> winNumbers, int bonusNumber) {
        this.lottoNumbers = new LottoNumbers(winNumbers);
        validateDuplicate(winNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(List<Integer> winNumbers, int bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_EXCEPTION);
        }
    }

    public int countMatchNumber(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.countMatchNumber(lottoNumbers);
    }

    public Boolean bonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.bonusMatch(this.bonusNumber);
    }
}
