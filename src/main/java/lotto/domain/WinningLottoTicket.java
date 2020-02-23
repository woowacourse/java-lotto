package lotto.domain;

import lotto.domain.errors.ErrorMessage;

import java.util.List;

public class WinningLottoTicket extends LottoTicket {
    private LottoNumber bonusNumber;

    public WinningLottoTicket(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
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


    public boolean isBonusMatched(LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .anyMatch(userLottoNumber -> bonusNumber.equals(userLottoNumber));
    }

    public long countMatched(WinningLottoTicket winningLotto, LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .filter(userLottoNumber -> winningLotto.isMatched(userLottoNumber))
                .count();
    }

    private boolean isMatched(LottoNumber userLottoNumber) {
        return lottoNumbers.contains(userLottoNumber);
    }
}
