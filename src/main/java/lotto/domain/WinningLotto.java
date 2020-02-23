package lotto.domain;

import lotto.domain.errors.ErrorMessage;

import java.util.List;

public class WinningLotto extends Lotto {
    private LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        super(winningNumbers);
        validateDistinctBonus(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDistinctBonus(LottoNumber bonusNumber) {
        if (super.lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getNumber())) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER.getMessage());
        }
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
