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

    private void validateDistinctBonus(final LottoNumber bonusNumber) {
        if (isBonusNumberAlreadyExist(bonusNumber)) {
            ErrorMessage nowErrorMessage = ErrorMessage.DUPLICATE_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private boolean isBonusNumberAlreadyExist(final LottoNumber bonusNumber) {
        return super.lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getNumber());
    }

    public boolean isBonusMatched(final LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .anyMatch(userLottoNumber -> bonusNumber.equals(userLottoNumber));
    }

    public long countMatched(final WinningLottoTicket winningLotto, final LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .filter(winningLotto::isMatched)
                .count();
    }

    private boolean isMatched(final LottoNumber userLottoNumber) {
        return lottoNumbers.contains(userLottoNumber);
    }
}
