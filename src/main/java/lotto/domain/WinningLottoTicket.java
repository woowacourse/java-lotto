package lotto.domain;

import lotto.validator.Validator;

import java.util.List;

public class WinningLottoTicket extends LottoTicket {
    private LottoNumber bonusNumber;

    public WinningLottoTicket(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        super(winningNumbers);
        Validator.validateDistinctBonus(super.lottoNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public boolean isBonusMatched(final LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .anyMatch(bonusNumber::equals);
    }

    public long countMatchedNumber(final WinningLottoTicket winningLotto, final LottoTicket userLottoTicket) {
        return userLottoTicket.getLottoNumbers().stream()
                .filter(winningLotto::isMatched)
                .count();
    }

    private boolean isMatched(final LottoNumber userLottoNumber) {
        return lottoNumbers.contains(userLottoNumber);
    }
}
