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
        if (isBonusNumberAlreadyExist(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER.getMessage());
        }
    }

    private boolean isBonusNumberAlreadyExist(LottoNumber bonusNumber) {
        return super.lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getNumber());
    }


    public boolean isBonusMatched(Lotto userLotto) {
        return userLotto.getLottoNumbers().stream()
                .anyMatch(userLottoNumber -> bonusNumber.equals(userLottoNumber));
    }

    public long countMatched(WinningLotto winningLotto, Lotto userLotto) {
        return userLotto.getLottoNumbers().stream()
                .filter(userLottoNumber -> winningLotto.isMatched(userLottoNumber))
                .count();
    }

    private boolean isMatched(LottoNumber userLottoNumber) {
        return lottoNumbers.contains(userLottoNumber);
    }
}
