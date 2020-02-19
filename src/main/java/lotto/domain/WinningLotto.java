package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private static final String BONUS_NUMBER_ALREADY_EXIST_ERROR_MSG = "보너스 번호가 당첨번호와 중복됩니다.";
    int bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, int bonusNumber) {
        super(winningNumbers);
        validateDistinctBonus(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDistinctBonus(int bonusNumber) {
        if (super.lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .anyMatch(winningNumber -> winningNumber == bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_EXIST_ERROR_MSG);
        }
    }
}
