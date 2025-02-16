package model;

import java.util.List;

public class WinLotto {
    public static final String BONUS_NUMBER_DUPLICATE_EXCEPTION = "당첨 번호와 중복이 아닌 숫자를 입력해주세요";

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
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_EXCEPTION);
        }
    }
}
